package com.integrador.msvc_monopatines.repository;

import com.integrador.msvc_monopatines.domain.Monopatin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MonopatinRepository extends MongoRepository<Monopatin, String> {
    List<Monopatin> findByEstado(int estado);
}