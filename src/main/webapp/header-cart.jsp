<div class="col-12 d-flex justify-content-between align-items-center text-success-emphasis mb-3">
    <h1 class="ms-5">La pizza di Ilyusha</h1>
    <%
        String cssClass = "hidden";
        Integer totalPizzas = (Integer) session.getAttribute("totalPizzas");
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        if (totalPizzas != null && totalPizzas > 0) {
            cssClass = "d-flex";
        }
    %>
    <div id="cartPanel" class="<%=cssClass%> me-5 fs-5 align-items-center">
        Ordered<span>&nbsp;</span><b><span id="totalPizzas"><%=totalPizzas%></span></b>
        <span>&nbsp;</span>pizza,<span>&nbsp;</span>total<span>&nbsp;</span>cost
        <b><span>&nbsp;$</span><span id="totalPrice"><%=String.format("%.2f",totalPrice)%></span></b>
        <span>&nbsp;</span>
        <a href="<%=request.getParameter("path")%>" class="btn btn-success">Cart<span>&nbsp;</span>
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                 class="bi bi-basket-fill" viewBox="0 0 16 16">
                <path d="M5.071 1.243a.5.5 0 0 1 .858.514L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15.5a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 6h1.717zM3.5 10.5a.5.5 0 1 0-1 0v3a.5.5 0 0 0 1 0zm2.5 0a.5.5 0 1 0-1 0v3a.5.5 0 0 0 1 0zm2.5 0a.5.5 0 1 0-1 0v3a.5.5 0 0 0 1 0zm2.5 0a.5.5 0 1 0-1 0v3a.5.5 0 0 0 1 0zm2.5 0a.5.5 0 1 0-1 0v3a.5.5 0 0 0 1 0z"/>
            </svg>
        </a>
    </div>
</div>
