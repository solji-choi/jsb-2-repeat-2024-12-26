package com.ll.sbb2.domain.board.question.service;

import com.ll.sbb2.domain.board.question.entity.Question;
import com.ll.sbb2.domain.board.question.repository.QuestionRepository;
import com.ll.sbb2.global.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);

        if(question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content) {
        Question q = Question
                .builder()
                .subject(subject)
                .content(content)
                .createDate(LocalDateTime.now())
                .build();

        this.questionRepository.save(q);
    }
}
