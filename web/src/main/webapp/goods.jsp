<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <link href="css/StyleReg.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/order.js"></script>
    <title>Редактирование товаров</title>
</head>
<body>
<div class="dominator" onclick="hideFormGood();"></div>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <table id="modifyE">
            <tr>
                <td>ID</td>
                <td>Brand</td>
                <td>Model</td>
                <td>Price</td>
                <td>ReleaseDate</td>
                <td>Picture</td>
                <td>Department</td>
                <td colspan="2"><div class="modifyC" onclick="addGood();">Добавить товар</div></td>
            </tr>
            <c:forEach var="point" items="${list}">
                <tr id="element${point.getId()}">
                    <td id="id${point.getId()}">${point.getId()}</td>
                    <td id="brand${point.getId()}">${point.getBrand()}</td>
                    <td id="model${point.getId()}">${point.getModel()}</td>
                    <td id="price${point.getId()}">${point.getPrice()}</td>
                    <td id="releaseDate${point.getId()}">${point.getReleaseDate()}</td>
                    <td id="picture${point.getId()}">${point.getImg()}</td>
                    <td id="fk${point.getId()}">${point.getFk_Catalog()}</td>
                    <td>
                        <div class="modifyB" onclick="modifyGood(${point.getId()});">Редактировать</div>
                    </td>
                    <td>
                        <div class="modifyD" onclick="deleteGood(${point.getId()});">Удалить</div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="megaDominator1">
        <form id="editGoods" action="do?command=ModifyGoods" method="post">
            <table class="registrationTable">
                <tr>
                    <td colspan="2"><div class="tableName" >Редактирование/добавление товара</div></td>
                </tr>
                <tr>
                    <td><input type="hidden" id="id" name="ID"/></td>
                </tr>
                <tr>
                    <td><label>Brand</label></td>
                    <td><input type="text" id="brand" name="Brand"/></td>
                </tr>
                <tr>
                    <td><label>Model</label></td>
                    <td><input type="text" id="model" name="Model"/></td>
                </tr>
                <tr>
                    <td><label>Price</label></td>
                    <td><input type="text" id="price" name="Price"/></td>
                </tr>
                <tr>
                    <td><label>ReleaseDate</label></td>
                    <td><input type="text" id="releaseDate" name="ReleaseDate"/></td>
                </tr>
                <tr>
                    <td><label>Picture</label></td>
                    <td><input type="text" id="picture" name="Picture"/></td>
                </tr>
                <tr>
                    <td><label>Department</label></td>
                    <td><input type="text" id="fk" name="Department"/></td>
                </tr>
                <!--Отправка формы-->
                <tr>
                    <td></td>
                    <td><div class="butn"><input class="button_ok" type="submit" value="Применить"/></div></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="footer">
        <div id="bottom_message"><p>${message}</p></div>
        <div id="author_info"><p>All rights reserved © 2016-2017™ by Dmitry V</p></div>
    </div>
</div>
</body>
</html>
