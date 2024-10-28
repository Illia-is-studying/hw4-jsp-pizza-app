<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Ingredient" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Position" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hw4jsppizzaapp.Services.Helpers.CssClassConverter" %>
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
                            List<Ingredient> ingredients = pizza.getPizza().getIngredients();
                            for (int i = 1; i < ingredients.size(); i++) {
                                String ingredientName = ingredients.get(i).getName();
                                List<Position> positions = pizza.getIngredientsPosition().get(ingredientName);
                                String cssClass = CssClassConverter.convertStringToCssClass(ingredientName);
                        %>
                        <div id="more-<%=cssClass%>" class="hidden">
                            <%
                                for (Position position : positions) {
                                    if (ingredients.get(i).getName().equals("mushrooms")) {
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
                <div class="alert alert-warning" role="alert">
                    <%=errorMessage%>
                </div>
            </div>
            <% } %>
            <form method="post">
                <div class="mb-3">
                    <label for="pizzaName" class="form-label">Name of Pizza</label>
                    <input type="text" name="pizzaName" id="pizzaName" class="form-control"
                           value="<%=pizza.getPizza().getName()%>" required>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price $</label>
                    <input type="text" name="price" id="price" class="form-control"
                           value="<%=pizza.getPizza().getPrice()%>" required>
                </div>
                <div class="mb-3">
                    <label class="from-label">Ingredients:</label>
                </div>
                <div class="mb-3">
                    <%
                        Ingredient defaultIngredient = ingredients.get(0);
                    %>
                    <div class="mb-3">
                        <input type="hidden" name="ingredients" value="<%=defaultIngredient.getId()%>">
                        <input type="checkbox" class="btn-check"
                               id="<%=defaultIngredient.getName()%>" name="ingredients"
                               autocomplete="off" checked disabled>
                        <label class="btn btn-secondary" for="<%=defaultIngredient.getName()%>">
                            <%=defaultIngredient.getName()%>
                        </label>
                    </div>
                    <%
                        int count = 3;
                        for (int i = 1; i < ingredients.size(); i++) {
                            String ingredientName = ingredients.get(i).getName();
                            String cssClass = CssClassConverter.convertStringToCssClass(ingredientName);
                            if (count == 0) {
                                count = 3;
                    %></div>
                <div class="mb-3"><%
                    }
                    count--;
                %>
                    <input type="checkbox" class="btn-check toggle-checkbox"
                           id="<%=cssClass%>" name="ingredients" value="<%=ingredients.get(i).getId()%>"
                           autocomplete="off"
                           data-target="more-<%=cssClass%>">
                    <label class="btn btn-outline-dark" for="<%=cssClass%>"><%=ingredients.get(i).getName()%>
                    </label>
                    <%
                        }
                    %>
                </div>
                <input type="submit" class="btn btn-outline-primary mx-2" value="Create">
                <a href="<%=request.getContextPath()%>" class="btn btn-outline-secondary mx-2">Go Back</a>
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
