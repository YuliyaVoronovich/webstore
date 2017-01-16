<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link rel="stylesheet" media="all" href="css/StyleTable.css"/>
    <link href="css/default.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="js/order.js"></script>
    <script src="js/mobilyslider.js" type="text/javascript"></script>
    <script src="js/init.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <title>Подробное описание</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <div id="content_descriptionF">
            <div id="inside">
                <div id="brandModel">${list.get(1)} ${list.get(2)}</div>
                <div id="forTable1">
                    <table id="info1">
                        <tr>
                            <td>Дата выхода на рынок</td>
                            <td>${list.get(4)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(6)}</td>
                            <td>${list.get(7)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(8)}</td>
                            <td>${list.get(9)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(10)}</td>
                            <td>${list.get(11)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(12)}</td>
                            <td>${list.get(13)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(14)}</td>
                            <td>${list.get(15)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(16)}</td>
                            <td>${list.get(17)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(18)}</td>
                            <td>${list.get(19)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(20)}</td>
                            <td>${list.get(21)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(22)}</td>
                            <td>${list.get(23)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(24)}</td>
                            <td>${list.get(25)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(26)}</td>
                            <td>${list.get(27)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(28)}</td>
                            <td>${list.get(29)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(30)}</td>
                            <td>${list.get(31)}</td>
                        </tr>
                        <tr>
                            <td>${list.get(32)}</td>
                            <td>${list.get(33)}</td>
                        </tr>
                    </table>
                </div>
                <div id="orderDuplicate">
                    <table>
                        <tr>
                            <td id="anotherTwo">
                                Стоимость: ${list.get(3)} BYN
                            </td>
                        </tr>
                        <tr>
                            <td id="anotherOne">
                                <div id="order" class="buttonOrder" onclick="addOrderAdd(${list.get(0)});">Добавить в
                                    корзину
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="mes" id="mes">Требуется авторизация</div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="warranty">Информация о товаре предоставлена для ознакомления и не является публичной офертой.
                    Информация о внешнем виде, характеристиках и комплектации товара обновляется с не-
                    которой временной задержкой. Просим вас отнестись с пониманием к данному факту и
                    заранее приносим извинения за возможные неточности в описании и фотографиях товара.
                    С целью предоставления достоверной информации - уточняйте, пожалуйста, все важные
                    для Вас параметры товары у консультанта при оформлении заказа.
                </div>
            </div>
            <div class="slider slider1">
                <div class="sliderContent">
                    <div class="item">
                        <img class="imgD" src="img/fridge/${list.get(5)}-1.jpg" alt=""/>
                    </div>
                    <div class="item">
                        <img class="imgD" src="img/fridge/${list.get(5)}-2.jpg" alt=""/>
                    </div>
                    <div class="item">
                        <img class="imgD" src="img/fridge/${list.get(5)}-3.jpg" alt=""/>
                    </div>
                    <div class="item">
                        <img class="imgD" src="img/fridge/${list.get(5)}-4.jpg" alt=""/>
                    </div>
                </div>
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
