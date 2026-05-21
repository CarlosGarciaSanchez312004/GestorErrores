package com.twindustry.gestorerrores.specification;

import com.twindustry.gestorerrores.model.*;
import com.twindustry.gestorerrores.model.Error;
import org.springframework.data.jpa.domain.Specification;

public class ErrorSpecification {
    public static Specification<Error> hasArea(Area area) {
        return (root, query, criteriaBuilder) -> {
            if (area == null) return null; // filtro ignorado
            return criteriaBuilder.equal(root.get("area"), area);
        };
    }
    public static Specification<Error> hasReportedBy(User reportedBy) {
        return (root, query, criteriaBuilder) -> {
            if (reportedBy == null) return null;
            return criteriaBuilder.equal(root.get("reportedBy"), reportedBy);
        };
        }
    public static Specification<Error> hasStatus(Status status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) return null;
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
    public static Specification<Error>  hasTopic(Topic topic) {
        return (root, query, criteriaBuilder) ->  {
            if (topic == null) return null;
            return criteriaBuilder.equal(root.get("topic"), topic);
        };
    }
    public static Specification<Error>  hasProyect(Proyect proyect) {
        return (root, query, criteriaBuilder) -> {
            if (proyect == null) return null;
            return criteriaBuilder.equal(root.get("proyect"), proyect);
        };
    }
}

