package com.ll.sbb2.domain.board.answer.controller;

import com.ll.sbb2.domain.board.answer.service.AnswerService;
import com.ll.sbb2.domain.board.question.entity.Question;
import com.ll.sbb2.domain.board.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model,
            @PathVariable Integer id,
            String content
    ) {
        Question question = this.questionService.getQuestion(id);

        this.answerService.create(question, content);

        return "redirect:/question/detail/%s".formatted(id);
    }
}
