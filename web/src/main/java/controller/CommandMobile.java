package controller;

import com.voronovich.entity.MainInfoEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static controller.FormHelper.methodBodyGood;

public class CommandMobile implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.MOBILE.inPage;
        Service service = Service.getService();
        List<MainInfoEntity> allGoods = service.getMainInfoService().get();
        List<MainInfoEntity> mobile = new ArrayList<>();
        for (MainInfoEntity mainInfoEntity : allGoods) {
            if (mainInfoEntity.getFk_Catalog() == 4) {
                mobile.add(mainInfoEntity);
            }
        }
        request.setAttribute("mobile", mobile);

        if (FormHelper.isPost(request)) {
            methodBodyGood(request, service);
        }
        return page;
    }
}
