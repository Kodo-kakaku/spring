package com.otus.studentstest.service;

import com.otus.studentstest.service.fileReaderService.ReadCsvFileServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

//@DisplayName("CSV Reader test")
//@SpringBootTest
class ReadCsvFileServiceTest  {
    @Autowired
    private ReadCsvFileServiceImpl service;

    @Test
    @DisplayName("Read file from resources")
    void shouldReadFileFromResources() {
        File file = new File(getClass().getClassLoader().getResource("questions.csv").getFile());

        assertThat(file).isEqualTo(service.readResourceFile());
    }

}