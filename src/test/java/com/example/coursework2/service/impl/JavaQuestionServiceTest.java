package com.example.coursework2.service.impl;

import com.example.coursework2.exception.QuestionAlreadyExistsException;
import com.example.coursework2.exception.QuestionNotFoundException;
import com.example.coursework2.model.Question;
import com.example.coursework2.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;


public class JavaQuestionServiceTest {

    private final  QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void afterEach(){
       Collection <Question> questions = questionService.getAll();
       questions.forEach(questionService::remove);

    }

    @Test
    public void addTest(){
        assertThat(questionService.getAll()).isEmpty();

        Question expected1 = new Question("Q1","A1");
        Question expected2 = new Question("Q2","A2");

        questionService.add(expected1);
        questionService.add(expected2.getQuestion(),expected2.getAnsver());

        assertThat(questionService.getAll()).hasSize(2);
        assertThat(questionService.getAll()).contains(expected1,expected2);
    }

    @Test
    public void addNegativeTest(){
        assertThat(questionService.getAll()).isEmpty();

        Question expected = addOneQuestion();

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(expected));
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(expected.getQuestion(),expected.getAnsver()));
    }

    @Test
    public void removeTest(){
        assertThat(questionService.getAll()).isEmpty();

        Question expected = addOneQuestion();

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q2","A2")));

        questionService.remove(expected);
        assertThat(questionService.getAll()).isEmpty();
    }

    @Test
    public void getRandomQuestionTest(){
        assertThat(questionService.getAll()).isEmpty();
        int size = 5;
        for (int i = 0; i <= size; i++) {
           addOneQuestion("Q"+i, "A"+i);
        }
        assertThat(questionService.getAll()).hasSize(size);
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    private Question addOneQuestion(String question, String ansver){
        int size = questionService.getAll().size();

        Question expected = new Question(question,ansver);
        questionService.add(expected);

        assertThat(questionService.getAll()).hasSize(size + 1);
        assertThat(questionService.getAll()).contains(expected);

        return expected;
    }
    private Question addOneQuestion(){
        return addOneQuestion("Q1","A1");
    }


}
