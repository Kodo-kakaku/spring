package com.otus.studentstest.service.fileReaderService;

import au.com.bytecode.opencsv.CSVReader;
import com.otus.studentstest.config.ApplicationSettings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class ReadCsvFileServiceImpl implements ReadCsvFileService {
    private final ApplicationSettings applicationSettings;

    public ReadCsvFileServiceImpl(ApplicationSettings applicationSettings) {
        this.applicationSettings = applicationSettings;
    }

    @Override
    public File readResourceFile() {
        System.out.println(applicationSettings.getFilePath());
        return new File(getClass().getClassLoader().getResource(applicationSettings.getFilePath()).getFile());
    }

    @Override
    public CSVReader getCSVReader(File file) throws FileNotFoundException {
        return new CSVReader(new FileReader(file), ';', '"', 1);
    }

    @Override
    public CSVReader getCSVReaderFromResourceFile() throws FileNotFoundException {
        return getCSVReader(readResourceFile());
    }
}
