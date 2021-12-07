package com.example.task1;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

public class test {

    public static void main(String[] args) throws MessagingException {
        String USER_NAME = "evgenyperov";  // GMail user name (just the part before "@gmail.com")
         String PASSWORD = "*********"; // GMail password for My Email
        String RECIPIENT = "someone1@gmail.com";
        String RECIPIENT2 = "someone2@gmail.com";
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT, RECIPIENT2 };
        String host = "smtp.gmail.com";

        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getDefaultInstance(props, null);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setSentDate(new Date());

        InternetAddress[] toAddress = new InternetAddress[to.length];
        // To get the array of addresses
        for( int i = 0; i < to.length; i++ ) {
            toAddress[i] = new InternetAddress(to[i]);
        }
        for( int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }

        message.setSubject("Тема письма");
        message.setText("Привет! Это письмо от Деда мороза!");

//авторизуемся на сервере:
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);

//
        //отправляем сообщение:
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
