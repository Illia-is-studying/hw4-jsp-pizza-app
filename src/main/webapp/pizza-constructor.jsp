<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Ingredient" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Position" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hw4jsppizzaapp.Services.CssClassConverter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PizzaViewModel pizza = (PizzaViewModel) request.getAttribute("pizza");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizzas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="resources/css/style.css">
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-6 d-flex justify-content-center">
            <div class="pizza-container">
                <div class="pizza-base">
                    <div class="pizza-edge"></div>
                    <div class="cheese">
                        <%
                            for (Ingredient ingredient : pizza.getPizzaIngredientsList()) {
                                List<Position> positions = pizza.getIngredientsPosition().get(ingredient.getName());
                                String cssClass = CssClassConverter.convertStringToCssClass(ingredient.getName());
                        %>
                        <div id="more-<%=cssClass%>" class="hidden">
                            <%
                                for (Position position : positions) {
                                    if (ingredient.getName().equals("mushrooms")) {
                            %>
                            <div class="<%=cssClass%>"
                                 style="<%="left: "+position.getLeft()+"%;" +
                               "top: "+position.getTop()+"%;" +
                               "transform: rotate("+position.getRotate()+"deg);"%>">
                                <div class="cap"></div>
                                <div class="stem"></div>
                            </div>
                            <% } else { %>
                            <div class="<%=cssClass%>"
                                 style="<%="left: "+position.getLeft()+"%;" +
                               "top: "+position.getTop()+"%;" +
                               "transform: rotate("+position.getRotate()+"deg);"%>"></div>
                            <%
                                    }
                                }
                            %>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6">
            <% if (errorMessage != null) { %>
            <div class="mb-3">
                <div class="alert alert-success" role="alert">
                    <%=errorMessage%>
                </div>
            </div>
            <% } %>
            <form method="post">
                <div class="mb-3">
                    <label for="pizzaName" class="form-label">Name of Pizza</label>
                    <input type="text" name="pizzaName" id="pizzaName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="from-label">Ingredients:</label>
                </div>
                <div class="mb-3">
                    <%
                        int count = 3;
                        for (Ingredient ingredient : pizza.getPizzaIngredientsList()) {
                            String cssClass = CssClassConverter.convertStringToCssClass(ingredient.getName());
                            if (count == 0) {
                                count = 3;
                    %></div>
                <div class="mb-3"><%
                    }
                    count--;
                %>
                    <input type="checkbox" class="btn-check toggle-checkbox"
                           id="<%=cssClass%>" name="ingredients" value="<%=ingredient.getId()%>" autocomplete="off"
                           data-target="more-<%=cssClass%>">
                    <label class="btn btn-outline-dark" for="<%=cssClass%>"><%=ingredient.getName()%>
                    </label>
                    <%
                        }
                    %>
                </div>
                <input type="submit" class="btn btn-primary" value="Save">
                <span> | </span>
                <a href="<%=request.getContextPath()%>" class="btn btn-secondary">Go Back</a>
            </form>
        </div>
    </div>
</div>
<script>
    document.querySelectorAll('.toggle-checkbox').forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            const targetDiv = document.getElementById(this.dataset.target);

            if (this.checked) {
                targetDiv.classList.remove('hidden');
            } else {
                targetDiv.classList.add('hidden');
            }
        });
    });
</script>
</body>
</html>
