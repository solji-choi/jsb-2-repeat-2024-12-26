package com.ll.sbb2.domain.question.answer.controller;

import com.ll.sbb2.domain.question.answer.form.AnswerForm;
import com.ll.sbb2.domain.question.answer.service.AnswerService;
import com.ll.sbb2.domain.question.question.entity.Question;
import com.ll.sbb2.domain.question.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model,
            @PathVariable Integer id,
            @Valid AnswerForm answerForm,
            BindingResult bindingResult
            ) {
        Question question = this.questionService.getQuestion(id);

        if(bindingResult.hasErrors()) {
            model.addAttribute("question", question);

            return "/domain/question/question/question_detail";
        }

        this.answerService.create(question, answerForm.getContent());

        return "redirect:/question/detail/%s".formatted(id);
    }
}
