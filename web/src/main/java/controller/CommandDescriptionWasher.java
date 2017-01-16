package controller;

import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;

import static controller.FormHelper.methodBodyDescription;

public class CommandDescriptionWasher implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.DESCRIPTIONWASHER.inPage;
        Service service = Service.getService();

        methodBodyDescription(request,service);

        return page;
    }
}
