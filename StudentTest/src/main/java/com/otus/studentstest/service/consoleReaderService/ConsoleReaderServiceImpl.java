package com.otus.studentstest.service.consoleReaderService;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class ConsoleReaderServiceImpl implements ConsoleReaderService {
    private BufferedReader reader;

    public ConsoleReaderServiceImpl(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public ConsoleReaderServiceImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readInputString() throws IOException {
        return reader.readLine();
    }
}
