/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendEmail(String to, String subject, String body) {
        final String username = "codesgamess@gmail.com"; // замени на свою почту codesgamess@gmail.com password:codegame там двухфакторка так что вы не зайдете, это чтобы я не забыл
        final String password = "pdrg blbt nnhr utip";   

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // От кого
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Кому
            message.setSubject(subject); // Тема
            message.setText(body); // Текст

            Transport.send(message);
            System.out.println("Письмо отправлено на: " + to);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
