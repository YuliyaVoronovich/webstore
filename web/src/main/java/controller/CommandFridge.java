package controller;

import com.voronovich.entity.MainInfoEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static controller.FormHelper.methodBodyGood;

/**
 * Class implements the content and functionality of the page fridge
 *
 * @author Dmitry V
 * @version 1.0
 */
class CommandFridge implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.FRIDGE.inPage;
        Service service = Service.getService();
        List<MainInfoEntity> allGoods = service.getMainInfoService().get();
        List<MainInfoEntity> fridge = new ArrayList<>();
        for (MainInfoEntity mainInfoEntity : allGoods) {
            if (mainInfoEntity.getFk_Catalog() == 1) {
                fridge.add(mainInfoEntity);
            }
        }
        request.setAttribute("fridge", fridge);

        if (FormHelper.isPost(request)) {
            methodBodyGood(request, service);
        }
        return page;
    }
}