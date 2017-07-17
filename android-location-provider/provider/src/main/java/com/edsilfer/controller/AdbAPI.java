package com.edsilfer.controller;

import com.edsilfer.domain.entity.Device;
import com.edsilfer.domain.entity.RequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ferna on 6/3/2017.
 */
public class AdbAPI {

    private static final String COMMAND_LIST_DEVICES = "adb devices";

    private static Logger logger = LoggerFactory.getLogger(AdbAPI.class.getName());

    public RequestResponse listDevices() {
        List<Device> devices = new ArrayList<>();
        String consoleOutput = getCurrentTimestampFormatted() + " Executing: " + COMMAND_LIST_DEVICES + "\n";

        try {
            Process p = Runtime.getRuntime().exec(COMMAND_LIST_DEVICES);
            p.waitFor();

            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int count = 0;
            while ((line = input.readLine()) != null) {
                if (count != 0 && line.contains("device")) {
                    devices.add(new Device(line.split("device")[0].trim()));
                }

                if (!line.isEmpty()) {
                    consoleOutput += getCurrentTimestampFormatted() + " " + line;
                    if (!line.contains("\n")) {
                        consoleOutput += "\n";
                    }
                }
                count++;
            }

            input.close();

            OutputStream outputStream = p.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println();
            printStream.flush();
            printStream.close();

            return new RequestResponse(consoleOutput, devices);
        } catch (Exception e) {
            logger.error("Unable to process command: " + e.getMessage());
            consoleOutput += getCurrentTimestampFormatted() + e.getMessage();
        }
        return new RequestResponse(consoleOutput.trim(), devices);
    }

    public RequestResponse sendDeviceLocation(String provider, String device, Double latitude, Double longitude) {
        StringBuilder command = new StringBuilder();
        command.append("adb -s ").append(device);
        command.append(" shell am broadcast -a \"android.intent.action.MOCKED_LOCATION\" ");
        command.append("--ef latitude ").append(latitude);
        command.append(" --ef longitude ").append(longitude);
        command.append(" --es provider ").append(provider);
        String consoleOutput = getCurrentTimestampFormatted() + " Executing: " + command.toString()  + "\n";

        try {
            Process p = Runtime.getRuntime().exec(command.toString());
            p.waitFor();

            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.isEmpty()) {
                    consoleOutput += getCurrentTimestampFormatted() + " " + line + "\n";
                }
            }

            input.close();

            OutputStream outputStream = p.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println();
            printStream.flush();
            printStream.close();
        } catch (Exception e) {
            logger.error("Unable to process command: " + e.getMessage());
            consoleOutput += getCurrentTimestampFormatted() + e.getMessage();
        }
        return new RequestResponse(consoleOutput.trim(), null);
    }

    private String getCurrentTimestampFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        return formatter.format(new Date());
    }

}
