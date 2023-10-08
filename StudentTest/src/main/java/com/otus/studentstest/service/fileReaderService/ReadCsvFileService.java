package com.otus.studentstest.service.fileReaderService;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;

public interface ReadCsvFileService {
    File readResourceFile();

    CSVReader getCSVReader(File file) throws FileNotFoundException;

    CSVReader getCSVReaderFromResourceFile() throws FileNotFoundException;
}
