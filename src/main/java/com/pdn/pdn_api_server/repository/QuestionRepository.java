package com.pdn.pdn_api_server.repository;

import com.pdn.pdn_api_server.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByClassRoom_ClassName(String className);
}
