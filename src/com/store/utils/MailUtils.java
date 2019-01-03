package com.store.utils;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.store.entity.User;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {
	private static String emailName="mxc182714899@163.com";//发件人邮箱  
	private static String password="78679567123m";//发件人邮箱的密码
	private static String title="用户激活";//邮箱标题
	private static String content; //邮箱内容
	private static String smtp="smtp.163.com";//发件人邮箱Smtp地址

	public static void sendMail(User user,String email, String emailMsg)
			throws AddressException, MessagingException {
		System.out.println(email);

		//0.1 确定连接位置
        Properties props = new Properties();
        //获取163邮箱smtp服务器的地址，
        props.setProperty("mail.host", "smtp.163.com");
        //是否进行权限验证。
        props.setProperty("mail.smtp.auth", "true");
        
        
        //0.2确定权限（账号和密码）
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //填写自己的163邮箱的登录帐号和授权密码，授权密码的获取，在后面会进行讲解。
                return new PasswordAuthentication(emailName,password);
            }
        };

        //1 获得连接
        /**
         * props：包含配置信息的对象，Properties类型
         *         配置邮箱服务器地址、配置是否进行权限验证(帐号密码验证)等
         * 
         * authenticator：确定权限(帐号和密码)        
         * 
         * 所以就要在上面构建这两个对象。
         */
        
        Session session = Session.getDefaultInstance(props, authenticator);
      //2 创建消息
        Message message = new MimeMessage(session);
        // 2.1 发件人        xxx@163.com
        message.setFrom(new InternetAddress(emailName));
        /**
         * 2.2 收件人 
         *         第一个参数：
         *             RecipientType.TO    代表收件人 
         *             RecipientType.CC    抄送
         *             RecipientType.BCC    暗送
         *         比如A要给B发邮件，但是A觉得有必要给要让C也看看其内容，就在给B发邮件时，
         *         将邮件内容抄送给C，那么C也能看到其内容了，但是B也能知道A给C抄送过该封邮件
         *         而如果是暗送(密送)给C的话，那么B就不知道A给C发送过该封邮件。
         *     第二个参数
         *         收件人的地址，或者是一个Address[]，用来装抄送或者暗送人的名单。或者用来群发。可以是相同邮箱服务器的，也可以是不同的
         *         这里我们发送给我们的qq邮箱
         */
        message.setRecipient(RecipientType.TO, new InternetAddress(email));
        // 2.3 主题（标题）
        message.setSubject("邮件的标题");
        // 2.4 正文
        String str = user.getUsername()+"： <br/>" +
                        "您好，您在本元旦狂欢注册用户，点击下面url进行激活<br/>" +
                        "http://localhost:8080/store/activate?mailcode="+user.getCode()+"<br/> "+
                        "如果不能点击，请复制直接激活<br/>" +
                        "如果不是本人，请删除邮件";
        //设置编码，防止发送的内容中文乱码。
        message.setContent(str, "text/html;charset=UTF-8");
      //3发送消息
        Transport.send(message);
	}
	
}
