package com.ll.sbb2.domain.board.question.service;

import com.ll.sbb2.domain.board.question.entity.Question;
import com.ll.sbb2.domain.board.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
}
