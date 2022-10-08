package com.example.coursework2.service.impl;

import com.example.coursework2.exception.IncorrectAmountOfQuestionException;
import com.example.coursework2.model.Question;
import org.apache.el.stream.Stream;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collection;
import java.util.stream.Stream;



@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void beforeEach(){
        Collection<Question> questions = Stream.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5")
        );
        when(javaQuestionService.getAll().thenReturn(questions));
    }

    @ParameterizedTest
    @MethodSource("negativeParams")
    public void getQuestionsNegativeTest(int incorrectAmount){
        assertThatExceptionOfType(IncorrectAmountOfQuestionException.class)
                .isThrownBy(()->examinerService.getQuestions(incorrectAmount));

//        assertThatExceptionOfType(IncorrectAmountOfQuestionException.class)
//                .isThrownBy(()->examinerService.getQuestions(6));
    }

    @Test
    public void getQuestionsTest(){
        List <Question> questions = new ArrayList<>(javaQuestionService.getAll());
                when(javaQuestionService.getRandomQuestion()).thenReturn(
                        questions.get(0),
                        questions.get(1),
                        questions.get(0),
                        questions.get(2),
                        questions.get(1)
                );
        assertThat(examinerService.getQuestions(3)).containsExastly();
    }

    public static Stream<Arguments> negativeParams(){
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(6),
                Arguments.of(0),
                Arguments.of(9)
        );
    }

}
