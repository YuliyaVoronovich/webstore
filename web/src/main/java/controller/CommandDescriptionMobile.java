package controller;

import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;

import static controller.FormHelper.methodBodyDescription;

public class CommandDescriptionMobile implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.DESCRIPTIONMOBILE.inPage;
        Service service = Service.getService();

        methodBodyDescription(request,service);

        return page;
    }

}
