package az.neuron.ask.util;

import az.neuron.ask.domain.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 5/22/13
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailUtility {
    private static String from = "Support@ultra.az";
    private static String host = "172.10.57.112";
    private static String port = "25";

    public void sendHtmlMail(User user){
        String to = user.getUserEmail();
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try{
            MimeMessage message = new MimeMessage(session);
            String encodingOptions = "text/html; charset=UTF-8";
            message.setHeader("Content-Type", encodingOptions);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Təsdiq bildirişi! - ALISA", "UTF-8");
            String personStatus = "bəy";
            if(user.getSex()==259){
                personStatus = "xanım";
            }

            String content1 = "<div style=\"border: 1px solid #CCCCCC; min-height: 200px; padding: 10px 20px; width: 400px; border-radius:10px; background:#EFEFEF\">\n" +
                    "<div class=\"im\">\n" +
                    "<h3 style=\"margin:10px 0 20px; color:#2F3551;\">Hörmətli "+user.getFirstName()+" "+personStatus+" şifrəniz yenilənmişdir</h3>\n" +
                    "<h4 style=\"margin:5px 0; font-weight:normal; color:#2F3551;\">İstifadəçi adi: "+user.getUserName()+"</h4>\n" +
                    "<h4 style=\"margin:5px 0; font-weight:normal; color:#2F3551;\">Şifrə: "+user.getPassword()+"</h4><br>\n" +
                    "</div>\n";

            String content2 = "<div style=\"border: 1px solid #CCCCCC; min-height: 130px; padding: 10px 20px; width: 400px; border-radius:10px; background:#EFEFEF\">\n" +
                    "<div class=\"im\">\n" +
                    "<h3 style=\"margin:10px 0 20px; color:#2F3551;\">Təsdiq mətni: "+user.getSuccessRandomText() + "</h3>\n" +
                    "</div>\n";

            String content3 = "<h3 style=\"border-top: 1px solid #CCCCCC; color:#2F3551; font-size: 15px; font-weight: normal; margin: 0; padding: 15px 0 0; font-style:italic\">URL: <a target=\"_blank\" href='"+user.getProjectURL()+"' style=\"font-weight:normal; color: #2F3551;\">"+user.getProjectURL()+"</a></h3>\n" +
                    "<div class=\"yj6qo\"></div><div class=\"adL\"><br></div></div><div class=\"adL\">\n" +
                    "</div>";
            String content = "";
            if(user.getMailSendType().equals("forgotPassword")){
                 content = content2 + content3;
            } else if(user.getMailSendType().equals("infoNewPassword")){
                 content = content1 + content3;
            }
            message.setContent(content, "text/html;charset=UTF-8");
            Transport.send(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public String randomTextGenerate(){
        char[] chars = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }
}
