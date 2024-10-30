<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Topping" %>
<%@ page import="java.util.List" %>
<%
    PizzaViewModel pizzaViewModel = (PizzaViewModel) request.getAttribute("pizza");
    List<Topping> toppings = (List<Topping>) request.getAttribute("toppings");
%>
<div class="card border-warning mx-2" style="width: 18rem;">
    <div class="card-header border-warning">Topping</div>

    <jsp:include page="pizza-layout.jsp"></jsp:include>

    <div class="text-center card-body">
        <h5 class="card-title"><%=pizzaViewModel.getPizza().getName()%>
        </h5>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="btn-group" role="group">
                <% for (Topping topping : toppings) { %>
                <input type="checkbox" class="btn-check" name="pizza<%=pizzaViewModel.getModelId()%>"
                       id="<%=topping.getName() + pizzaViewModel.getModelId()%>"
                       autocomplete="off" value="<%=topping.getId()%>"
                       onclick="changePrice(this,'<%="price" + pizzaViewModel.getModelId()%>',
                           <%=topping.getPrice()%>)">
                <label class="btn btn-outline-dark"
                       for="<%=topping.getName() + pizzaViewModel.getModelId()%>">
                    <%=topping.getName()%>
                </label>
                <%
                    }
                %>
            </div>
        </li>
    </ul>
    <div class="card-footer text-center">
        <strong class="text-body-secondary text-primary">
            <span>$</span>
            <span id="<%="price" + pizzaViewModel.getModelId()%>">
            <%=String.format("%.2f", pizzaViewModel.getPizza().getPrice())%>
            </span>
        </strong>
    </div>
</div>
