<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%
    PizzaViewModel pizzaViewModel = (PizzaViewModel) request.getAttribute("pizza");
%>
<div class="card mx-2" style="width: 18rem;">
    <jsp:include page="pizza-layout.jsp"></jsp:include>

    <div class="text-center card-body">
        <h5 class="card-title"><%=pizzaViewModel.getPizza().getName()%>
        </h5>
        <p class="card-text">
            <strong>Ingredients: </strong>
            <%=pizzaViewModel.getPizzaIngredientsLine()%>
        </p>
        <button onclick="addToCart(<%=pizzaViewModel.getPizza().getId()%>,<%=pizzaViewModel.getPizza().getPrice()%>)"
                class="btn btn-outline-success mx-2">
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
        </button>
        <%
            Long pizzaId = pizzaViewModel.getPizza().getId();
            if (pizzaId > 4) {
        %>
        <a href="<%=request.getContextPath()%>?method=delete&id=<%=pizzaId%>"
           class="btn btn-outline-danger mx-2">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                 class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
            </svg>
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash3-fill"
                 viewBox="0 0 16 16">
                <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
            </svg>
        </a>
        <%
            }
        %>
    </div>
    <div class="card-footer text-center">
        <strong class="text-body-secondary text-primary">
            $<%=String.format("%.2f", pizzaViewModel.getPizza().getPrice())%>
        </strong>
    </div>
</div>
