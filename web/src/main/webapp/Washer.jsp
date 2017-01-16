<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="js/order.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <title>Стиральные машины</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <div id="department">
            <div id="insideDepartment">
                <c:forEach var="good" items="${washer}">
                    <div class="element">
                        <div class="picture1">
                            <img class="picture" src="img/washer/${good.getImg()}.jpg"/>
                        </div>
                        <div class="des1">
                            <div class="brand">${good.getBrand()} ${good.getModel()}</div>
                            <div class="des2">
                                Доставка:
                                Самовывоз, По городу - Бесплатно. А также Экспресс-доставка по Минску в течение 3 часов!
                                Подробнее на сайте., По области - Выберите свой населенный пункт и рассчитайте стоимость
                                доставки на сайте, По Беларуси - Выберите свой населенный пункт и рассчитайте стоимость
                                доставки на сайте
                                Оплата:
                                Наличный расчет, Банковская карта, Система «Расчет» (ЕРИП), Почтовый перевод
                            </div>
                            <div class="descriptionGood"><a class="a1"
                                                            href="do?command=DescriptionWasher&id=${good.getId()}"
                                                            target="_blank">Перейти к
                                описанию...</a></div>
                        </div>
                        <div class="order">
                            <div class="price">${good.getPrice()} BYN</div>
                            <div class="price1"><div id="order${good.getId()}" class="buttonOrder" onclick="addOrder(${good.getId()});">Добавить в корзину</div>
                            </div>
                            <div class="price0"><div class="mes" id="mes${good.getId()}">Требуется авторизация</div></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div id="footer">
        <div id="bottom_message"><p>${message}</p></div>
        <div id="author_info"><p>All rights reserved © 2016-2017™ by Dmitry V</p></div>
    </div>
</div>
</body>
</html>
