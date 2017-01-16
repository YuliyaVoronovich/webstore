package controller;

import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.UsersEntity;
import com.voronovich.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;

/**
 * Class designed for reading data from forms and it's validation
 *
 * @author Dmitry V
 * @version 1.0
 */
public class FormHelper {

    /**
     * This method is for debugging. It will show data from forms
     *
     * @param request HttpServletRequest
     * @return text
     */
    static String strDebugForm(HttpServletRequest request) {
        String formText = "";
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String field = parameterNames.nextElement();
            String value = request.getParameter(field);
            formText = formText.concat(field + "=" + value + "; ");
        }
        return formText;
    }

    /**
     * Data validation from form with default pattern
     *
     * @param value String
     * @return boolean
     */
    static boolean valid(String value) {
        String defaultPattern = ".*";
        return valid(value, defaultPattern);
    }

    /**
     * Data validation from form with specified pattern
     *
     * @param value   String
     * @param pattern String
     * @return boolean
     */
    private static boolean valid(String value, String pattern) {
        return (value != null) && value.matches(pattern);
    }

    /**
     * Method checks for availability of post method
     *
     * @param req HttpServletRequest
     * @return boolean
     */
    static boolean isPost(HttpServletRequest req) {
        return (req.getMethod() != null && req.getMethod().equalsIgnoreCase("post"));
    }

    static boolean getString(HttpServletRequest req,
                             String parameter,
                             String pattern) throws ParseException {
        String value = req.getParameter(parameter);
        if (value != null) {
            if (value.matches(pattern))
                return true;
            else
                throw new ParseException("Incorrect String: " + parameter, 0);
        }
        return false;
    }

    static int getInt(HttpServletRequest req,
                      String parameter) throws ParseException {
        String value = req.getParameter(parameter);
        if (value != null) {
            if (value.matches("[0-9-]+")) {
                return (Integer.parseInt(value));
            }
        }
        throw new ParseException("Incorrect String: " + parameter, 0);
    }

    public static void methodBodyDescription(HttpServletRequest request, Service service) {
        if (isPost(request)) {
            methodBodyGood(request, service);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            List<String> mainInfoViewEntity = service.getMainInfoViewService().getMainInfoView(id);
            request.setAttribute("list", mainInfoViewEntity);
        }
    }

    public static void methodBodyGood(HttpServletRequest request, Service service) {
        UsersEntity usersEntity = (UsersEntity) request.getSession(true).getAttribute("user");
        if (usersEntity == null) {
            request.setAttribute(Action.msgMessage, "Для работы с корзиной требуется авторизация...");
        } else {
            int user = usersEntity.getId();
            int good = Integer.parseInt(request.getParameter("neID"));
            BasketEntity basketEntity = new BasketEntity(0, user, good);
            service.getBasketService().create(basketEntity);
        }
    }
}
