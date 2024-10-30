<%@ page import="java.util.List" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<PizzaViewModel> pizzas = (List<PizzaViewModel>) request.getAttribute("pizzas");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Toppings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <jsp:include page="header-cart.jsp">
            <jsp:param name="path" value="cart"/>
        </jsp:include>

        <form action="toppings" method="POST">
            <div class="col d-flex justify-content-center mb-3">
                <%
                    int count = 4;
                    for (PizzaViewModel pizza : pizzas) {
                        request.setAttribute("pizza", pizza);
                        if (count == 0) {
                            count = 4;
                %></div>
            <div class="col d-flex justify-content-center mb-3"><%
                }
                count--;
            %>
                <jsp:include page="/topping-card.jsp"></jsp:include>
                <%
                    }
                %>
            </div>
            <div class="col-12 d-flex justify-content-center mb-3">
                <input type="submit" class="btn btn-outline-primary mx-2" value="Save and go to order">
                <a href="<%=request.getContextPath()%>" class="btn btn-outline-secondary mx-2">Back To Pizzas</a>
            </div>
        </form>
    </div>
</div>
<script src="resources/js/script.js"></script>
</body>
</html>
