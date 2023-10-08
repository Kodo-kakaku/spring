package com.otus.studentstest.service;

import com.otus.studentstest.config.ApplicationSettings;
import com.otus.studentstest.domain.Student;
import com.otus.studentstest.service.consoleReaderService.ConsoleReaderService;
import com.otus.studentstest.service.consoleWriterService.ConsoleWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;

import java.io.IOException;
import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final ConsoleReaderService readConsoleService;

    private final ConsoleWriterService outputQuestionsService;

    private final ApplicationSettings applicationSettings;

    private final MessageSource messageSource;

    //private int correctAnswerCount;

    @Override
    public void startTesting() throws IOException {
        int questionCount = outputQuestionsService.getCountQuestions();
        int correctAnswerCount = 0;

        for (int i = 0; i < questionCount; i++) {
            outputQuestionsService.outputQuestion(i);
            System.out.println(messageSource.getMessage("student.answer", null, Locale.US));

            if (checkCorrectAnswer(readConsoleService.readInputString(), outputQuestionsService.getSolutionAnswer(i))) {
                correctAnswerCount++;
            }
        }

        if (correctAnswerCount >= applicationSettings.getTrueAnswers())
            System.out.println(messageSource.getMessage("test.result.completed", null, Locale.US));
        else
            System.out.println(messageSource.getMessage("test.result.failed", null, Locale.US));

        System.out.println(messageSource.getMessage("student.result",
                new Object[]{correctAnswerCount + "/" + questionCount}, Locale.US));
    }

    @Override //todo test
    public Student loginStudent() throws IOException {
        Student student = new Student();

        System.out.println(messageSource.getMessage("student.hello", null, Locale.US));
        student.setName(readConsoleService.readInputString());

        return student;
    }

    private boolean checkCorrectAnswer(String answer, String solution) {
        return answer.equalsIgnoreCase(solution);
    }
}