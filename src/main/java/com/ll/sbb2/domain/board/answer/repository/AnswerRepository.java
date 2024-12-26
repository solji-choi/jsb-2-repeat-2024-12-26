package com.ll.sbb2.domain.board.answer.repository;

import com.ll.sbb2.domain.board.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
