package com.ll.sbb2.domain.question.answer.repository;

import com.ll.sbb2.domain.question.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
