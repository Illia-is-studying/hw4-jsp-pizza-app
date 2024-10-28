<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Ingredient" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Position" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hw4jsppizzaapp.Services.Helpers.CssClassConverter" %>
<%
    PizzaViewModel pizzaViewModel = (PizzaViewModel) request.getAttribute("pizza");
%>
<div class="pizza-container">
    <div class="pizza-base">
        <div class="pizza-edge"></div>
        <div class="cheese">
            <%
                for (Ingredient ingredient : pizzaViewModel.getPizza().getIngredients()) {
                    List<Position> positions = pizzaViewModel.getIngredientsPosition().get(ingredient.getName());

                    for (Position position : positions) {
                        if (ingredient.getName().equals("mushrooms")) { %>
            <div class="<%=CssClassConverter.convertStringToCssClass(ingredient.getName())%>"
                 style="<%="left: "+position.getLeft()+"%;" +
                               "top: "+position.getTop()+"%;" +
                               "transform: rotate("+position.getRotate()+"deg);"%>">
                <div class="cap"></div>
                <div class="stem"></div>
            </div>
            <% } else { %>
            <div class="<%=CssClassConverter.convertStringToCssClass(ingredient.getName())%>"
                 style="<%="left: "+position.getLeft()+"%;" +
                               "top: "+position.getTop()+"%;" +
                               "transform: rotate("+position.getRotate()+"deg);"%>"></div>
            <%
                        }
                    }
                }
            %>
        </div>
    </div>
</div>