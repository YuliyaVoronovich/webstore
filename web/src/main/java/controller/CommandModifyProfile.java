package controller;

import com.voronovich.entity.UsersEntity;
import com.voronovich.service.Service;
import hash.RandomSalt;
import hash.Sha256;
import org.apache.commons.lang3.StringUtils;
import patterns.Patterns;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ResourceBundle;

public class CommandModifyProfile implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        int fk_id_default = 1;
        String page = Action.MODIFYPROFILE.inPage;
        Service service = Service.getService();

        if ((request.getSession(true).getAttribute("user")) == null) {
            request.setAttribute(Action.msgMessage, "Для редактирования профиля требуется авторизация");
        } else {
            UsersEntity usersEntity = (UsersEntity) request.getSession(true).getAttribute("user");
            int id_default = usersEntity.getId();
            String login_before = usersEntity.getLogin();
            String email_before = usersEntity.getEmail();
            try {
                if (FormHelper.isPost(request)) {
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String email = request.getParameter("email");
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    String blockValue = "false";
                    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)
                            || StringUtils.isEmpty(email) || StringUtils.isEmpty(login)
                            || StringUtils.isEmpty(password)) {
                        request.setAttribute(Action.msgMessage, "Для изменения вашего аккаунта заполните все данные.");
                        return page;
                    } else if (!StringUtils.isEmpty(service.getUserService().get(login).getLogin()) &&
                            !(service.getUserService().get(login).getLogin()).equals(login_before)) {
                        request.setAttribute(Action.msgMessage, "Пользователь не создан. Данный логин занят.");
                        page = Action.MODIFYPROFILE.inPage;
                    } else if (!StringUtils.isEmpty(service.getUserService().getByEmail(email).getEmail()) &&
                            !(service.getUserService().getByEmail(email).getEmail()).equals(email_before)) {
                        request.setAttribute(Action.msgMessage, "Пользователь не создан. Данный Email занят.");
                        page = Action.MODIFYPROFILE.inPage;
                    } else if (FormHelper.getString(request, "name", Patterns.NAME) &&
                            FormHelper.getString(request, "surname", Patterns.SURNAME) &&
                            FormHelper.getString(request, "email", Patterns.EMAIL) &&
                            FormHelper.getString(request, "login", Patterns.LOGIN) &&
                            FormHelper.getString(request, "password", Patterns.PASSWORD)) {
                        String salt = RandomSalt.csRandomAlphaNumericString(20);
                        String staticSalt = ResourceBundle.getBundle("staticSalt").getString("staticSalt");
                        String password2 = Sha256.sha256(password + salt + staticSalt);

                        usersEntity.setId(id_default);
                        usersEntity.setFk_Role(fk_id_default);
                        usersEntity.setName(name);
                        usersEntity.setSurname(surname);
                        usersEntity.setEmail(email);
                        usersEntity.setLogin(login);
                        usersEntity.setPassword(password2);
                        usersEntity.setSalt(salt);
                        usersEntity.setBlackList(blockValue);

                        if (service.getUserService().update(usersEntity)) {
                            request.getSession().invalidate();
                            request.setAttribute(
                                    Action.msgMessage,
                                    "Пользователь изменен. Введите данные для авторизации.");
                            page = Action.LOGIN.inPage;
                        } else {
                            request.setAttribute(
                                    Action.msgMessage,
                                    "Пользователь НЕ создан. Введите данные заново.");
                            page = Action.MODIFYPROFILE.inPage;
                        }
                    }
                }
            } catch (ParseException e) {
                request.setAttribute(Action.msgMessage, "Пользователь НЕ создан. Проверьте формат данных.");
            }
        }
        return page;
    }
}
