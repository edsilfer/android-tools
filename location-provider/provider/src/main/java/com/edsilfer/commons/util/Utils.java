package com.edsilfer.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by ferna on 6/3/2017.
 */
public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class.getName());

    public static void logExecutionResult(Process p) throws IOException {
        String line;
        BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        while ((line = error.readLine()) != null) {
            System.out.println(line);
        }
        error.close();

        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            logger.info(line);
        }

        input.close();

        OutputStream outputStream = p.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println();
        printStream.flush();
        printStream.close();
    }

}
