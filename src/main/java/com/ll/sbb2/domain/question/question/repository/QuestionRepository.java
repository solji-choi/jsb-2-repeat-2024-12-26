package com.ll.sbb2.domain.question.question.repository;

import com.ll.sbb2.domain.question.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String suject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);
}
