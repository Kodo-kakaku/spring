package com.otus.studentstest.service;

import com.otus.studentstest.StudentsTestApplication;
import com.otus.studentstest.config.ApplicationSettings;
import com.otus.studentstest.service.consoleReaderService.ConsoleReaderServiceImpl;
import com.otus.studentstest.service.consoleWriterService.ConsoleWriterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс проведения тестирования студентов")
@ContextConfiguration(classes = StudentsTestApplication.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class StudentServiceTest {
    StudentServiceImpl service;
    @Autowired
    private ConsoleWriterService consoleWriterService;
    @Autowired
    private MessageSource messageSource;

    @Mock
    private ApplicationSettings applicationSettings;

    @Test
    @DisplayName("Метод тестирования студентов")
    void shouldTakeTheTest() throws IOException {
        Mockito.when(applicationSettings.getFilePath()).thenReturn("questions.csv");
        Mockito.when(applicationSettings.getTrueAnswers()).thenReturn(4);
        Mockito.when(applicationSettings.getLocale()).thenReturn("en_US");

        String data = """
                4
                6
                8
                10
                13
                """;

        //Оборачиваем строку в класс ByteArrayInputStream
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        ConsoleReaderServiceImpl readConsoleService = new ConsoleReaderServiceImpl(inputStream);
        service = new StudentServiceImpl(readConsoleService, consoleWriterService, applicationSettings, messageSource);
        service.startTesting();

        assertEquals(4, service.getCorrectAnswerCount()); // 4 правильных ответов из 5
    }

    @Test
    @DisplayName("Логирование студентов")
    void shouldLoginStudent() throws IOException {
        Mockito.when(applicationSettings.getFilePath()).thenReturn("questions.csv");
        String data = "Ivan\n";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        ConsoleReaderServiceImpl readConsoleService = new ConsoleReaderServiceImpl(inputStream);

        service = new StudentServiceImpl(readConsoleService, consoleWriterService, applicationSettings, messageSource);

        var studentLogin = service.loginStudent();
        assertEquals("Ivan", studentLogin.getName());
    }
}
