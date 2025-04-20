package com.pdn.pdn_api_server.repository;

import com.pdn.pdn_api_server.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    Optional<Class> findByClassName(String className);
    void deleteByClassName(String className);
}
