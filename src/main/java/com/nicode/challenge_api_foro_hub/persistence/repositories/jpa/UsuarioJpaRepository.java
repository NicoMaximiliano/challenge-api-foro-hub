package com.nicode.challenge_api_foro_hub.persistence.repositories.jpa;

import com.nicode.challenge_api_foro_hub.persistence.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    boolean existsById(Long id);
    boolean existsByIdAndNombreAndEmail(Long id, String nombre, String email);
}
