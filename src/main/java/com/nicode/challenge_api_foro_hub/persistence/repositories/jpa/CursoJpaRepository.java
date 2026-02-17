package com.nicode.challenge_api_foro_hub.persistence.repositories.jpa;

import com.nicode.challenge_api_foro_hub.persistence.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CursoJpaRepository extends JpaRepository<CursoEntity, Long> {

}
