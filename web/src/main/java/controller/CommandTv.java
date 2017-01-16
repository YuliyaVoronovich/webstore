package controller;

import com.voronovich.entity.MainInfoEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static controller.FormHelper.methodBodyGood;

/**
 * Class implements the content and functionality of the page tv
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandTv implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.TV.inPage;
        Service service = Service.getService();
        List<MainInfoEntity> allGoods = service.getMainInfoService().get();
        List<MainInfoEntity> tv = new ArrayList<>();
        for (MainInfoEntity mainInfoEntity : allGoods) {
            if (mainInfoEntity.getFk_Catalog() == 2) {
                tv.add(mainInfoEntity);
            }
        }
        request.setAttribute("tv", tv);

        if (FormHelper.isPost(request)) {
            methodBodyGood(request, service);
        }
        return page;
    }
}
