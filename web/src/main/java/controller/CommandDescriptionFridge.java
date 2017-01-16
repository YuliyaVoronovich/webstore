package controller;

import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;

import static controller.FormHelper.methodBodyDescription;

/**
 * Class implements the content and functionality of the page fridge description
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandDescriptionFridge implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.DESCRIPTIONFRIDGE.inPage;
        Service service = Service.getService();

        methodBodyDescription(request,service);

        return page;
    }
}
