package com.ll.sbb2.domain.board.question.controller;

import com.ll.sbb2.domain.board.question.entity.Question;
import com.ll.sbb2.domain.board.question.service.QuestionService;
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
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList", questionList);

        return "question_list";
    }
}
