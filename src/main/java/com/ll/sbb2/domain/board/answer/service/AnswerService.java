package com.ll.sbb2.domain.board.answer.service;

import com.ll.sbb2.domain.board.answer.entity.Answer;
import com.ll.sbb2.domain.board.answer.repository.AnswerRepository;
import com.ll.sbb2.domain.board.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = Answer
                .builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .question(question)
                .build();

        this.answerRepository.save(answer);
    }
}
