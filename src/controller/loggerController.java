package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class loggerController {

    public static void logger(String userName, Boolean success) throws IOException {

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        String log = (ZonedDateTime.now(ZoneOffset.UTC) + " Username: " +  userName + " Login Timestamp: " + timestamp + " Login success: " + success.toString() + "\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("login_activity", true))) {
            writer.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
}

