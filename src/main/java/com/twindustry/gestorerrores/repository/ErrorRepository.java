package com.twindustry.gestorerrores.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.twindustry.gestorerrores.model.Error;
public interface ErrorRepository extends JpaRepository<Error, Long> , JpaSpecificationExecutor<Error>{
}
