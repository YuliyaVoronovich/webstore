package controller;

import com.voronovich.entity.AdditionalInfoEntity;
import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.MainInfoEntity;
import com.voronovich.entity.UsersEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class implements the content and functionality of the page modify goods
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandModifyGoods implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.MODIFYGOODS.inPage;
        Service service = Service.getService();
        UsersEntity usersEntity = (UsersEntity) request.getSession(true).getAttribute("user");

        if (FormHelper.isPost(request)) {
            int id = Integer.parseInt(request.getParameter("ID"));

            if (id > 0) {
                MainInfoEntity mainInfoEntityUpdate = service.getMainInfoService().get(id);
                String brand = request.getParameter("Brand");
                String model = request.getParameter("Model");
                Double price = Double.parseDouble(request.getParameter("Price"));
                String release = request.getParameter("ReleaseDate");
                String picture = request.getParameter("Picture");
                int fk = Integer.parseInt(request.getParameter("Department"));
                mainInfoEntityUpdate.setId(id);
                mainInfoEntityUpdate.setBrand(brand);
                mainInfoEntityUpdate.setModel(model);
                mainInfoEntityUpdate.setPrice(price);
                mainInfoEntityUpdate.setReleaseDate(release);
                mainInfoEntityUpdate.setImg(picture);
                mainInfoEntityUpdate.setFk_Catalog(fk);
                if (service.getMainInfoService().update(mainInfoEntityUpdate)) {
                    request.setAttribute(Action.msgMessage, "Товар успешно обновлен");
                    page = Action.MODIFYGOODS.okPage;
                } else {
                    request.setAttribute(Action.msgMessage, "Товар не обновлен");
                    page = Action.MODIFYGOODS.inPage;
                }
            }
            if (id == 0) {
                MainInfoEntity mainInfoEntity = new MainInfoEntity();
                String brand = request.getParameter("Brand");
                String model = request.getParameter("Model");
                Double price = Double.parseDouble(request.getParameter("Price"));
                String release = request.getParameter("ReleaseDate");
                String picture = request.getParameter("Picture");
                int fk = Integer.parseInt(request.getParameter("Department"));
                mainInfoEntity.setId(id);
                mainInfoEntity.setBrand(brand);
                mainInfoEntity.setModel(model);
                mainInfoEntity.setPrice(price);
                mainInfoEntity.setReleaseDate(release);
                mainInfoEntity.setImg(picture);
                mainInfoEntity.setFk_Catalog(fk);
                if (service.getMainInfoService().create(mainInfoEntity)) {
                    request.setAttribute(Action.msgMessage, "Товар успешно добавлен");
                    page = Action.MODIFYGOODS.okPage;
                } else {
                    request.setAttribute(Action.msgMessage, "Товар не добавлен");
                    page = Action.MODIFYGOODS.inPage;
                }
            }
            if (id < 0) {
                id = (-1) * id;
                List<BasketEntity> basketEntityList = service.getBasketService().get();
                for (BasketEntity basketEntity : basketEntityList) {
                    if (basketEntity.getFk_MainInfo() == id) {
                        service.getBasketService().delete(basketEntity);
                    }
                }
                List<AdditionalInfoEntity> additionalInfoEntityList = service.getAdditionalInfoService().get();
                for (AdditionalInfoEntity additionalInfoEntity : additionalInfoEntityList) {
                    if (additionalInfoEntity.getFk_MainInfo() == id) {
                        service.getAdditionalInfoService().delete(additionalInfoEntity);
                    }
                }
                MainInfoEntity mainInfoEntityDelete = service.getMainInfoService().get(id);
                if (service.getMainInfoService().delete(mainInfoEntityDelete)) {
                    request.setAttribute(Action.msgMessage, "Товар успешно удален");
                    page = Action.MODIFYGOODS.okPage;
                } else {
                    request.setAttribute(Action.msgMessage, "Товар не удален");
                    page = Action.MODIFYGOODS.inPage;
                }
            }
        }
        if (usersEntity != null && usersEntity.getFk_Role() == 2) {
            List<MainInfoEntity> list = service.getMainInfoService().get();
            request.setAttribute("list", list);
        } else {
            request.setAttribute(Action.msgMessage, "Вы не авторизированы либо не обладаете правами администратора");
        }
        return page;
    }
}