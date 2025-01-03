package com.ll.sbb2.domain.question.question.service;

import com.ll.sbb2.domain.question.question.entity.Question;
import com.ll.sbb2.domain.question.question.repository.QuestionRepository;
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

    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    public Question findById(Integer id) {
        Optional<Question> opQuestion = this.questionRepository.findById(id);

        if(opQuestion.isPresent()) {
            return opQuestion.get();
        } else {
            throw new DataNotFoundException("존재하지 않는 id입니다.");
        }
    }

    public void create(String subject, String content) {
        Question question = Question
                .builder()
                .subject(subject)
                .content(content)
                .createDate(LocalDateTime.now())
                .build();

        this.questionRepository.save(question);
    }
}
