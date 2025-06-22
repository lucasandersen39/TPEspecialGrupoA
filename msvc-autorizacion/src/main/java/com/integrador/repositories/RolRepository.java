package com.integrador.repositories;

import java.util.Optional;

import com.integrador.entites.Rol;
import com.integrador.enums.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
	Optional<Rol> findByRole(final RolEnum rol);
}