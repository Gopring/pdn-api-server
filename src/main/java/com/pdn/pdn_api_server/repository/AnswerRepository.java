package com.pdn.pdn_api_server.repository;

import com.pdn.pdn_api_server.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion_Id(Long questionId);
}
