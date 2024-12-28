package com.ll.sbb2.domain.question.question.controller;

import com.ll.sbb2.domain.question.question.entity.Question;
import com.ll.sbb2.domain.question.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String getItems(Model model) {
        List<Question> questionList = this.questionService.findAll();

        model.addAttribute("questionList", questionList);

        return "/domain/question/question/question_list";
    }
}
