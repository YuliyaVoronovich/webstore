package controller;

import com.voronovich.entity.UsersEntity;
import com.voronovich.service.Service;
import hash.Sha256;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

/**
 * Class implements the content and functionality of the page login
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandLogin implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.LOGIN.inPage;
        int time_session = 900;
        UsersEntity usersEntity = new UsersEntity();
        Service service = Service.getService();

        if (FormHelper.isPost(request)) {
            String login = request.getParameter("Login");
            String password = request.getParameter("Password");
            if (!StringUtils.isEmpty(login) && !StringUtils.isEmpty(password)) {
                String salt = service.getUserService().get(login).getSalt();
                String staticSalt = ResourceBundle.getBundle("staticSalt").getString("staticSalt");
                String password1 = Sha256.sha256(password + salt + staticSalt);
                usersEntity = service.getUserService().get(login, password1);
                if (StringUtils.isEmpty(usersEntity.getName())) {
                    request.setAttribute(
                            Action.msgMessage,
                            "Неверные данные, повторите ввод.");
                    page = Action.LOGIN.inPage;
                } else if (StringUtils.equals(usersEntity.getBlackList(), "true")) {
                    request.setAttribute(
                            Action.msgMessage,
                            "Ваш профиль заблокирован за несоблюдение правил сайта, за дополнительной информацией обратитесь к администратору.");
                    page = Action.LOGIN.inPage;
                } else {
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(time_session);
                    session.setAttribute("user", usersEntity);
                    request.setAttribute(
                            Action.msgMessage,
                            "Добро пожаловать, " + usersEntity.getName());
                    page = Action.LOGIN.okPage;
                }
            } else {
                request.setAttribute(Action.msgMessage, "Для авторизации необходимы ввод логина и пароля.");
                page = Action.LOGIN.inPage;
            }
        } else {
            page = Action.LOGIN.inPage;
        }
        return page;
    }
}