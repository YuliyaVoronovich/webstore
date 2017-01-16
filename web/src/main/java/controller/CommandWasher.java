package controller;

import com.voronovich.entity.MainInfoEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static controller.FormHelper.methodBodyGood;

public class CommandWasher implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.WASHER.inPage;
        Service service = Service.getService();
        List<MainInfoEntity> allGoods = service.getMainInfoService().get();
        List<MainInfoEntity> washer = new ArrayList<>();
        for (MainInfoEntity mainInfoEntity : allGoods) {
            if (mainInfoEntity.getFk_Catalog() == 3) {
                washer.add(mainInfoEntity);
            }
        }
        request.setAttribute("washer", washer);

        if (FormHelper.isPost(request)) {
            methodBodyGood(request, service);
        }
        return page;
    }
}
