<%@ page import="entity.Cart" %><%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 03.05.2020
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Cart Page</title>
</head>

<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<%-- Здесь уже есть объект класса карта в Сессии, т.к.
мы его передали в классе SessionCartServlet при перенаправлении.. (Forward)--%>
<% Cart cart = (Cart) session.getAttribute("cart");

    String name = request.getParameter("name");
    int quantity = 0;
    try {
        quantity = Integer.parseInt(request.getParameter("quantity"));
    } catch (NumberFormatException ignore) {
    }

    String strResponse = "?name=" + name + "&quantity=" + quantity;
    if (cart == null) cart = new Cart(name, quantity);
    else strResponse = "?name=" + cart.getName() + "&quantity=" + cart.getQuantity();

    session.setAttribute("cart", cart); %>


<p> Ваш выбранный товар: <%= cart.getName()%></p>
<p> и его Кол-во: <%= cart.getQuantity()%></p>

<h4>  из предыдущего запроса: </h4>
<p>  товар: <%=name%></p>
<p> Кол-во: <%= quantity%></p>

<form action="./showCart.jsp"<%=strResponse%>>
<%--<form action="./hello-SessionCartServlet"<%=strResponse%>>--%>
    Имя товара: <input type="text" name="name"><br>
    Его кол-во: <input type="number" name="quantity"><br>
    <button type="submit">Отправить Параметры Заказа</button>
</form>

</body>
</html>
