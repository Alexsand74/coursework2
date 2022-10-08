package com.example.coursework2.model;


import java.util.Objects;

public class Question {
    private final String question;
    private final String ansver;

    public Question(String question, String ansver) {
        this.question = question;
        this.ansver = ansver;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnsver() {
        return ansver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Question))
            return false;
        Question question1 = (Question) o;
        return getQuestion().equals(question1.getQuestion()) && getAnsver().equals(question1.getAnsver());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getAnsver());
    }

    @Override
    public String toString() {
        return String.format("Вопрос: %s, ответ: %s ", question, ansver);
    }

}
