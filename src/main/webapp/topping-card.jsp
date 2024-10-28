<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.Enums.ToppingPriceEnum" %>
<%
    PizzaViewModel pizzaViewModel = (PizzaViewModel) request.getAttribute("pizza");

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
                <input type="checkbox" class="btn-check" name="pizza<%=pizzaViewModel.getModelId()%>"
                       id="olives<%=pizzaViewModel.getModelId()%>"
                       autocomplete="off" value="More Olives"
                       onclick="changePrice(this,'<%="price" + pizzaViewModel.getModelId()%>',
                           <%=ToppingPriceEnum.OLIVES.getValue()%>)">
                <label class="btn btn-outline-dark"
                       for="olives<%=pizzaViewModel.getModelId()%>">
                    More Olives
                </label>
                <input type="checkbox" class="btn-check" name="pizza<%=pizzaViewModel.getModelId()%>"
                       id="capers<%=pizzaViewModel.getModelId()%>"
                       autocomplete="off" value="More Capers"
                       onclick="changePrice(this, '<%="price" + pizzaViewModel.getModelId()%>',
                           <%=ToppingPriceEnum.CAPERS.getValue()%>)">
                <label class="btn btn-outline-dark"
                       for="capers<%=pizzaViewModel.getModelId()%>">
                    More Capers
                </label>
                <input type="checkbox" class="btn-check" name="pizza<%=pizzaViewModel.getModelId()%>"
                       id="cheese<%=pizzaViewModel.getModelId()%>"
                       autocomplete="off" value="Extra Cheese"
                       onclick="changePrice(this, '<%="price" + pizzaViewModel.getModelId()%>',
                           <%=ToppingPriceEnum.EXTRA_CHEESE.getValue()%>)">
                <label class="btn btn-outline-dark"
                       for="cheese<%=pizzaViewModel.getModelId()%>">
                    Extra Cheese
                </label>
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
