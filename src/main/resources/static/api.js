/* ============================================================
   api.js — Utilidades para llamadas a la API REST
   ============================================================
   Cambia API_BASE a la URL de tu backend Spring Boot
   ============================================================ */

const API_BASE = 'http://localhost:8080/api';

// ── Autenticación ─────────────────────────────────────────

function getToken() {
    return localStorage.getItem('token');
}

function getUsuarioActual() {
    const raw = localStorage.getItem('usuario');
    return raw ? JSON.parse(raw) : null;
}

function checkAuth() {
    if (!getToken()) {
        window.location.href = 'login.html';
        return null;
    }
    return getUsuarioActual();
}

function logout() {
    localStorage.clear();
    window.location.href = 'login.html';
}

// ── Petición base ──────────────────────────────────────────

async function request(endpoint, options = {}) {
    const config = {
        ...options,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${getToken()}`,
            ...(options.headers || {})
        }
    };

    const res = await fetch(`${API_BASE}${endpoint}`, config);

    if (res.status === 401) {
        localStorage.clear();
        window.location.href = 'login.html';
        return;
    }

    if (res.status === 204) return null;

    const data = await res.json().catch(() => ({}));

    if (!res.ok) {
        throw new Error(data.message || data.error || `Error ${res.status}`);
    }

    return data;
}

// ── Auth ───────────────────────────────────────────────────

const auth = {
    login: (email, password) =>
        fetch(`${API_BASE}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        }).then(r => r.json())
};

// ── Proyectos ──────────────────────────────────────────────

const proyectos = {
    listar:  ()     => request('/proyectos'),
    crear:   (data) => request('/proyectos', { method: 'POST', body: JSON.stringify(data) }),
    editar:  (id, data) => request(`/proyectos/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    borrar:  (id)   => request(`/proyectos/${id}`, { method: 'DELETE' })
};

// ── Áreas ──────────────────────────────────────────────────

const areas = {
    listar: () => request('/areas')
};

// ── Topics ─────────────────────────────────────────────────

const topics = {
    listar: () => request('/topics')
};

// ── Usuarios ───────────────────────────────────────────────

const usuarios = {
    listar:    ()     => request('/usuarios'),
    getById:   (id)   => request(`/usuarios/${id}`),
    crear:     (data) => request('/usuarios', { method: 'POST', body: JSON.stringify(data) }),
    habilitar: (id)   => request(`/usuarios/${id}/enable`, { method: 'PUT' }),
    deshabilitar: (id) => request(`/usuarios/${id}/disable`, { method: 'PUT' })
};

// ── Errores ────────────────────────────────────────────────

const errores = {
    /**
     * Listar con filtros opcionales:
     *   proyectoId, areaId, topicId, reportedBy, status
     */
    listar: (filtros = {}) => {
        const params = new URLSearchParams(
            Object.fromEntries(
                Object.entries(filtros).filter(([, v]) => v !== '' && v !== null && v !== undefined)
            )
        );
        const q = params.toString();
        return request(`/errores${q ? '?' + q : ''}`);
    },

    getById: (id)   => request(`/errores/${id}`),

    crear:   (data) => request('/errores', {
        method: 'POST',
        body: JSON.stringify(data)
    }),

    editar: (id, data) => request(`/errores/${id}`, {
        method: 'PUT',
        body: JSON.stringify(data)
    }),

    resolver: (id, solution, descriptionSolution) =>
        request(`/errores/${id}/resolver`, {
            method: 'PUT',
            body: JSON.stringify({ solution, descriptionSolution })
        }),

    reabrir: (id) => request(`/errores/${id}/reabrir`, { method: 'PUT' }),

    borrar:  (id) => request(`/errores/${id}`, { method: 'DELETE' })
};

// ── Comentarios ────────────────────────────────────────────

const comentarios = {
    listar: (errorId)           => request(`/errores/${errorId}/comentarios`),
    crear:  (errorId, content)  => request(`/errores/${errorId}/comentarios`, {
        method: 'POST',
        body: JSON.stringify({ content })
    }),
    borrar: (errorId, comentId) => request(`/errores/${errorId}/comentarios/${comentId}`, {
        method: 'DELETE'
    })
};

// ── Adjuntos ───────────────────────────────────────────────

const adjuntos = {
    listar: (errorId) => request(`/errores/${errorId}/adjuntos`),

    subir: (errorId, file) => {
        const formData = new FormData();
        formData.append('file', file);
        return fetch(`${API_BASE}/errores/${errorId}/adjuntos`, {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${getToken()}` },
            body: formData
        }).then(r => r.json());
    },

    borrar:   (errorId, adjuntoId) => request(`/errores/${errorId}/adjuntos/${adjuntoId}`, { method: 'DELETE' }),
    urlDescarga: (adjuntoId)       => `${API_BASE}/adjuntos/${adjuntoId}/descargar?token=${getToken()}`
};

// ── Helpers de fecha ────────────────────────────────────────

function formatFecha(dateStr) {
    if (!dateStr) return '—';
    return new Date(dateStr).toLocaleDateString('es-ES', {
        day: '2-digit', month: 'short', year: 'numeric',
        hour: '2-digit', minute: '2-digit'
    });
}

function tiempoRelativo(dateStr) {
    if (!dateStr) return '';
    const diff = (Date.now() - new Date(dateStr)) / 1000;
    if (diff < 60)    return 'ahora mismo';
    if (diff < 3600)  return `hace ${Math.floor(diff / 60)} min`;
    if (diff < 86400) return `hace ${Math.floor(diff / 3600)}h`;
    if (diff < 604800) return `hace ${Math.floor(diff / 86400)}d`;
    return formatFecha(dateStr);
}

// ── Helpers de UI ───────────────────────────────────────────

function iniciales(nombre, apellido) {
    return ((nombre?.[0] || '') + (apellido?.[0] || '')).toUpperCase() || '??';
}

function iconoAdjunto(fileType) {
    if (!fileType) return { clase: 'txt', icono: 'ti-file' };
    if (fileType.includes('pdf'))   return { clase: 'pdf', icono: 'ti-file-type-pdf' };
    if (fileType.includes('image')) return { clase: 'img', icono: 'ti-photo' };
    return { clase: 'txt', icono: 'ti-file-text' };
}

function mostrarError(msg, contenedor) {
    const el = document.getElementById(contenedor);
    if (el) el.innerHTML = `<div class="error-msg"><i class="ti ti-alert-circle"></i> ${msg}</div>`;
}

function mostrarCargando(contenedor, texto = 'Cargando...') {
    const el = document.getElementById(contenedor);
    if (el) el.innerHTML = `<div class="loading">${texto}</div>`;
}