package controller;

import com.voronovich.entity.UsersEntity;
import com.voronovich.service.Service;
import hash.RandomSalt;
import hash.Sha256;
import mail.Sender;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class CommandSendMail implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.SENDMAIL.inPage;
        String adminMail = "dd.versustest@gmail.com";
        String adminPassword = "numbnumb";
        String clientMail;
        Sender sslSender = new Sender(adminMail,adminPassword);
        Service service = Service.getService();

        if (FormHelper.isPost(request)) {
            clientMail = request.getParameter("Email");
            UsersEntity usersEntity = service.getUserService().getByEmail(clientMail);
            String login = usersEntity.getLogin();
            String password = RandomSalt.csRandomAlphaNumericString(7);
            String salt = RandomSalt.csRandomAlphaNumericString(20);
            String staticSalt = ResourceBundle.getBundle("staticSalt").getString("staticSalt");
            String password1= Sha256.sha256(password + salt + staticSalt);
            usersEntity.setPassword(password1);
            usersEntity.setSalt(salt);
            if(service.getUserService().update(usersEntity)){
                String subject = "Восстановление пароля";
                String text = "Здравствуйте, " + usersEntity.getName() + "! Ваш Login: " + login + "; Password: " + password + ".";
                sslSender.send(subject,text, clientMail);
                request.setAttribute(Action.msgMessage, "Данные отправлены на Ваш электронный адрес");
                page = Action.SENDMAIL.okPage;
            }
        }
        return page;
    }
}
