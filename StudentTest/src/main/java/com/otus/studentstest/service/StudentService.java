package com.otus.studentstest.service;

import com.otus.studentstest.domain.Student;

import java.io.IOException;

public interface StudentService {
    void startTesting() throws IOException;

    Student loginStudent() throws IOException;
}