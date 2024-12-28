package com.ll.sbb2.domain.question.answer.controller;

import com.ll.sbb2.domain.question.answer.service.AnswerService;
import com.ll.sbb2.domain.question.question.entity.Question;
import com.ll.sbb2.domain.question.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{questionId}")
    public String create(
            @PathVariable Integer questionId,
            String content
    ) {
        Question question = this.questionService.findById(questionId);

        this.answerService.create(question, content);

        return "redirect:/question/detail/%s".formatted(questionId);
    }
}