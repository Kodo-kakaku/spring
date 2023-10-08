package com.otus.studentstest.service;

import com.otus.studentstest.config.ApplicationSettings;
import com.otus.studentstest.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.io.IOException;
import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class ShellStudentService {
    private final StudentServiceImpl service;

    private Student student;

    private final MessageSource messageSource;

    private final ApplicationSettings applicationSettings;

    @ShellMethod(value = "User login", key = {"login", "l"})
    public void login() throws IOException {
        student = service.loginStudent();
    }

    @ShellMethod(value = "Start testing", key = {"start", "test", "s", "t"})
    @ShellMethodAvailability(value = "isLoginCorrect")
    public void startTest() throws IOException {
        service.startTesting();
    }

    private Availability isLoginCorrect() {
        return student != null ?
                Availability.available() :
                Availability.unavailable(messageSource.getMessage("shell.loginNotAvailable",
                        new Object[] {}, Locale.US));
    }

}