package com.vrc.logline;

import com.vrc.logline.processor.ExceptionProcessor;
import com.vrc.logline.repository.AllLines;
import org.junit.Test;

import java.io.File;

public class ExceptionProcessorTest {

    private ExceptionProcessor exceptionProcessor = new ExceptionProcessor();

    @Test
    public void shouldGroupStackTrace() throws Exception {
        String path = this.getClass().getResource("/exception.txt").getFile();
        AllLines allLines = new AllLines();
        allLines.addFileLinesFrom(new File(path));

        exceptionProcessor.process(allLines);
        for (String s : allLines.processedLines()) {
            System.out.println(s);
        }
    }
}
