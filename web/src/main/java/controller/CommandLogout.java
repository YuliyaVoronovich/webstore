package controller;

import javax.servlet.http.HttpServletRequest;

public class CommandLogout implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.LOGOUT.inPage;

        if(FormHelper.isPost(request)) {
            request.getSession().invalidate();
            request.setAttribute(Action.msgMessage, "Сеанс завершен");
            page = Action.LOGOUT.okPage;
        }
        return page;
    }
}
