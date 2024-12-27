package com.ll.sbb2.domain.question.question.controller;

import com.ll.sbb2.domain.question.answer.form.AnswerForm;
import com.ll.sbb2.domain.question.question.entity.Question;
import com.ll.sbb2.domain.question.question.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        return "/domain/question/question/question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            Model model,
            @PathVariable Integer id,
            AnswerForm answerForm
    ) {
        Question question = this.questionService.getQuestion(id);

        model.addAttribute("question", question);

        return "/domain/question/question/question_detail";
    }

    record QuestionForm(
            @NotEmpty(message = "제목은 필수항목입니다.")
            @Size(max = 200)
            String subject,

            @NotEmpty(message = "내용은 필수항목입니다.")
            String content
    ) {}

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "/domain/question/question/question_form";
    }

    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return "/domain/question/question/question_form";
        }

        this.questionService.create(questionForm.subject, questionForm.content);

        return "redirect:/question/list";
    }
}
