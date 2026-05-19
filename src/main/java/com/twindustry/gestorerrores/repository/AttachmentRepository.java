package com.twindustry.gestorerrores.repository;

import com.twindustry.gestorerrores.model.Attachment;
import com.twindustry.gestorerrores.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    List<Attachment> findByError(Error error);

    List<Attachment> findByFileType(String fileType);
}