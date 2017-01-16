package controller;

import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.MainInfoEntity;
import com.voronovich.entity.UsersEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the content and functionality of the page orders
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandBasket implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.BASKET.inPage;

        Service service = Service.getService();

        if ((request.getSession(true).getAttribute("user")) == null) {
            request.setAttribute(Action.msgMessage, "Для работы с корзиной требуется авторизация");
        } else {
            UsersEntity usersEntity = (UsersEntity) request.getSession(true).getAttribute("user");
            List<BasketEntity> list1 = service.getBasketService().getByUser(usersEntity.getId());
            if (list1.size() == 0) {
                request.setAttribute(Action.msgMessage, "Ваша корзина пуста");
            } else {
                List<MainInfoEntity> list = new ArrayList<>();
                for (BasketEntity basketEntity : list1) {
                    MainInfoEntity mainInfoEntity = service.getMainInfoService().get(basketEntity.getFk_MainInfo());
                    list.add(mainInfoEntity);
                }
                request.setAttribute("list", list);
            }
        }
        if (FormHelper.isPost(request)) {
            int id = Integer.parseInt(request.getParameter("neID"));
            BasketEntity basketEntity = service.getBasketService().getByGood(id);
            if (service.getBasketService().delete(basketEntity)) {
                request.setAttribute(Action.msgMessage, "Товар удален из корзины");
            } else {
                request.setAttribute(Action.msgMessage, "Товар не удален из корзины");
            }
        }
        return page;
    }
}