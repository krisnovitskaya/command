package ru.geekbrains.javacommand.command.util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * message url "https://api.telegram.org/bot1783218480:AAFMTNTnEtHCLfHJOgG9uLJkf7yiomMNeRo/sendMessage?chat_id=user&text=Hello+World";
 * Токен телеграм бота находится в application.yaml
 * Документация telegram bot api: https://tlgrm.ru/docs/bots/api
 * @author owpk
 */
@Component
@Slf4j
public class TelegramNotification {
    private static final String URL_FORMAT = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

    @Value("${bot-token}")
    private String token;

    /**
     * Отправляет сообщение от лица телеграм бота @errand_notification_bot,
     * для отпарвки сообщения конкретному пользователю необходим chat_id этого пользователя,
     * который можно узнать написав другому телеграм боту @chatid_echo_bot от лица этого пользователя
     * @param userId
     * @param message
     */
    public void sendToTelegram(String userId, String message) {
        String urlString = String.format(URL_FORMAT, token, userId, message);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String response = sb.toString();
            log.info(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
