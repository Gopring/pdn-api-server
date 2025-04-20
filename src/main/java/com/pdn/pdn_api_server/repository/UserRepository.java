package com.pdn.pdn_api_server.repository;

import com.pdn.pdn_api_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByClassRoom_ClassName(String className);
}
