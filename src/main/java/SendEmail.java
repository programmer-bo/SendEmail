import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) throws GeneralSecurityException, MessagingException {
        //读取配置文件
        Properties props=new Properties();
        //开启Debug
        props.setProperty("mail.debug","true");
        //发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        //发送邮件服务器的主机名
        props.setProperty("mail.host", "smtp.qq.com");
        //发送邮件协议
        props.setProperty("mail.transport.protocol", "smtp");
        //开启ssl加密（并不是所有的邮箱服务器都需要，但是qq邮箱服务器是必须的）
        MailSSLSocketFactory msf= new MailSSLSocketFactory();
        msf.setTrustAllHosts(true);

        props.put("mail.smtp.ssl.enable", "true");

        props.put("mail.smtp.ssl.socketFactory",msf);

        //获取Session会话实例（javamail Session与HttpSession的区别是Javamail的Session只是配置信息的集合）

        Session session=Session.getInstance(props,new javax.mail.Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1240020374@qq.com", "zqyjzceogehxiich");
            }
            });
        Message message=new MimeMessage(session);

        //设置邮件主题

        message.setSubject("这是主题");

        //封装需要发送电子邮件的信息

        message.setText("这是一封简单的电子邮件");

        //设置发件人地址

        message.setFrom(new InternetAddress("1240020374@qq.com"));

        //此类的功能是发送邮件 又会话获得实例

        Transport transport=session.getTransport();

        //开启连接

        transport.connect();

        //设置收件人地址邮件信息

        transport.sendMessage(message, new Address[]{new InternetAddress("1240020374@qq.com")});

        //邮件发送后关闭信息

        transport.close();
    }
}
