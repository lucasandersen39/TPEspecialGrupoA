package com.integrador.msvc_monopatines.repository;

import com.integrador.msvc_monopatines.domain.Monopatin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonopatinRepository extends MongoRepository<Monopatin, String> {
}

