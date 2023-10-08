package com.otus.studentstest.service.consoleWriterService;

import java.io.IOException;

public interface ConsoleWriterService {
    void outputAllQuestions() throws IOException;

    void outputQuestion(int questionNumber) throws IOException;

    String getSolutionAnswer(int questionNumber) throws IOException;

    int getCountQuestions() throws IOException;
}