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

public class CommandReg implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        int id_default = 0;
        int fk_id_default = 1;
        String page = Action.REG.inPage;
        Object object = null;
        UsersEntity userEntity = new UsersEntity();
        Service service = Service.getService();
        try {
            if (FormHelper.isPost(request)) {
                String name = request.getParameter("Name");
                String surname = request.getParameter("Surname");
                String email = request.getParameter("Email");
                String login = request.getParameter("Login");
                String password = request.getParameter("Password");
                String passwordRepeat = request.getParameter("PasswordRepeat");
                String blockValue = "false";
                if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)
                        || StringUtils.isEmpty(email) || StringUtils.isEmpty(login)
                        || StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordRepeat)) {
                    request.setAttribute(Action.msgMessage, "Для регистрации вашего аккаунта заполните все данные.");
                    return page;

                } else if (!StringUtils.isEmpty(service.getUserService().get(login).getLogin())) {
                    request.setAttribute(Action.msgMessage, "Пользователь не создан. Данный логин занят.");
                    page = Action.REG.inPage;
                } else if (!StringUtils.isEmpty(service.getUserService().getByEmail(email).getEmail())) {
                    request.setAttribute(Action.msgMessage, "Пользователь не создан. Данный Email занят.");
                    page = Action.REG.inPage;
                } else if (!StringUtils.equals(password,passwordRepeat)) {
                    request.setAttribute(Action.msgMessage, "Подтвердите пароль корректно.");
                    page = Action.REG.inPage;
                } else if (
                        FormHelper.getString(request, "Name", Patterns.NAME) &&
                        FormHelper.getString(request, "Surname", Patterns.SURNAME) &&
                        FormHelper.getString(request, "Email", Patterns.EMAIL) &&
                        FormHelper.getString(request, "Login", Patterns.LOGIN) &&
                        FormHelper.getString(request, "Password", Patterns.PASSWORD)) {
                    String salt = RandomSalt.csRandomAlphaNumericString(20);
                    String staticSalt = ResourceBundle.getBundle("staticSalt").getString("staticSalt");
                    String password1= Sha256.sha256(password + salt + staticSalt);
                    userEntity.setId(id_default);
                    userEntity.setFk_Role(fk_id_default);
                    userEntity.setName(name);
                    userEntity.setSurname(surname);
                    userEntity.setEmail(email);
                    userEntity.setLogin(login);
                    userEntity.setPassword(password1);
                    userEntity.setSalt(salt);
                    userEntity.setBlackList(blockValue);

                    if (service.getUserService().create(userEntity)) {
                        request.setAttribute(
                                Action.msgMessage,
                                "Пользователь создан. Введите данные для авторизации.");
                        page = Action.LOGIN.inPage;
                    } else {
                        request.setAttribute(
                                Action.msgMessage,
                                "Пользователь НЕ создан. Введите данные заново.");
                        page = Action.REG.inPage;
                    }
                }
            }
        } catch (ParseException e) {
            request.setAttribute(Action.msgMessage, "Пользователь НЕ создан. Проверьте формат данных.");
        }
        return page;
    }
}