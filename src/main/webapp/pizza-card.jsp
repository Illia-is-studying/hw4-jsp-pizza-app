<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Ingredient" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Position" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hw4jsppizzaapp.Services.CssClassConverter" %>
<%
    PizzaViewModel pizzaViewModel = (PizzaViewModel) request.getAttribute("pizza");
%>
<div class="card mx-2" style="width: 18rem;">
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
    <div class="text-center card-body">
        <h5 class="card-title"><%=pizzaViewModel.getPizza().getName()%>
        </h5>
        <p class="card-text">
            <strong>Ingredients: </strong>
            <%=pizzaViewModel.getPizzaIngredientsLine()%>
        </p>
        <a href="#" class="btn btn-outline-success">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                 class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
                <path
                        d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3z"/>
            </svg>
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                 class="bi bi-basket2-fill" viewBox="0 0 16 16">
                <path
                        d="M5.929 1.757a.5.5 0 1 0-.858-.514L2.217 6H.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h.623l1.844 6.456A.75.75 0 0 0 3.69 15h8.622a.75.75 0 0 0 .722-.544L14.877 8h.623a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1.717L10.93 1.243a.5.5 0 1 0-.858.514L12.617 6H3.383zM4 10a1 1 0 0 1 2 0v2a1 1 0 1 1-2 0zm3 0a1 1 0 0 1 2 0v2a1 1 0 1 1-2 0zm4-1a1 1 0 0 1 1 1v2a1 1 0 1 1-2 0v-2a1 1 0 0 1 1-1"/>
            </svg>
        </a>
    </div>
    <div class="card-footer text-center">
        <strong class="text-body-secondary text-primary">
            $<%=String.format("%.2f", pizzaViewModel.getPizza().getPrice())%>
        </strong>
    </div>
</div>
