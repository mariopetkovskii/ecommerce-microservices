package petkovskimariobachelor.notificationservice.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.shared.product.ProductResponseSharedDto;

import java.util.List;
import java.util.Properties;

@Service
public class EmailService {
    private static final String host = "localhost";
    private final static int port = 1025;
    private final static String sender = "e-commerce@commerce.com";

    public static void sendOrderEmail(String to, List<ProductResponseSharedDto> productListDtoList, Double totalPrice, String deliveryAddress){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, null);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Products: \n");
            productListDtoList.forEach(product -> {
                stringBuilder.append("--------------------------------------------\n");
                stringBuilder.append(product.name()).append(" - ").append(product.price()).append("$");
                stringBuilder.append("\n").append("Quantity: ").append(product.quantity());
                stringBuilder.append("\n--------------------------------------------");
                stringBuilder.append("\n");
            });
            stringBuilder.append("Delivery address: ").append(deliveryAddress);
            stringBuilder.append("\n").append("Total Price: ").append(totalPrice);
            message.setSubject("New order");
            message.setText(stringBuilder.toString());

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
