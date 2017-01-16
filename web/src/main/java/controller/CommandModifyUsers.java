package controller;

import com.voronovich.entity.UsersEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandModifyUsers implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.MODIFYUSERS.inPage;
        Service service = Service.getService();
        UsersEntity usersEntity = (UsersEntity) request.getSession(true).getAttribute("user");

        if(FormHelper.isPost(request)){
            int id = Integer.parseInt(request.getParameter("ID"));
            UsersEntity usersEntityUpdate = service.getUserService().get(id);
            String name = request.getParameter("Name");
            String surname = request.getParameter("Surname");
            String email = request.getParameter("Email");
            String login = request.getParameter("Login");
            String blackList = request.getParameter("BlackList");
            usersEntityUpdate.setId(id);
            usersEntityUpdate.setName(name);
            usersEntityUpdate.setSurname(surname);
            usersEntityUpdate.setEmail(email);
            usersEntityUpdate.setLogin(login);
            usersEntityUpdate.setBlackList(blackList);
            if(service.getUserService().update(usersEntityUpdate)){
                request.setAttribute(Action.msgMessage,"Пользователь успешно обновлен");
                page = Action.MODIFYUSERS.okPage;
            } else {
                request.setAttribute(Action.msgMessage,"Пользователь не обновлен");
                page = Action.MODIFYUSERS.inPage;
            }
        }

        if(usersEntity!=null && usersEntity.getFk_Role()==2){
            List<UsersEntity> list = service.getUserService().get();
            request.setAttribute("list",list);
        } else {
            request.setAttribute(Action.msgMessage, "Вы не авторизированы либо не облядаете правами администратора");
        }
        return page;
    }
}
