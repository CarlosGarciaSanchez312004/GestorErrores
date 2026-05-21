package com.twindustry.gestorerrores.repository;
import com.twindustry.gestorerrores.specification.ErrorSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.twindustry.gestorerrores.model.Error;
public interface ErrorRepository extends JpaRepository<Error, Long> , JpaSpecificationExecutor<ErrorSpecification>{
}
