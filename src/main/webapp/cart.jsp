<%@ page import="java.util.List" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.CartViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CartViewModel> cartViewModels = (List<CartViewModel>) request.getAttribute("cartViewModels");
    Double totalPrice = (Double) request.getAttribute("totalPizzasPrice");
    Integer totalPizzas = (Integer) session.getAttribute("totalPizzas");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div class="container">
    <main>
        <div class="row g-5 mt-5">
            <div class="col-md-5 col-lg-4 order-md-last">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-primary">Your cart</span>
                    <span class="badge bg-primary rounded-pill"><%=totalPizzas%></span>
                </h4>
                <ul class="list-group mb-3">
                    <% for (CartViewModel cartViewModel : cartViewModels) { %>
                    <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                            <h6 class="my-0"><%=cartViewModel.getPizzaName()%>
                            </h6>
                            <small class="text-body-secondary"><%=cartViewModel.getPlusToppings()%>
                            </small>
                        </div>
                        <span class="text-body-secondary">
                            $<%=String.format("%.2f", cartViewModel.getTotalPrice())%>
                        </span>
                    </li>
                    <%
                        }
                    %>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Total (USD)</span>
                        <strong>$<%=String.format("%.2f", totalPrice)%>
                        </strong>
                    </li>
                </ul>
            </div>
            <div class="col-md-7 col-lg-8">
                <h4 class="mb-3">Billing address</h4>
                <form action="cart" method="POST">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">First name</label>
                            <input type="text" class="form-control"
                                   name="firstName" id="firstName" required>
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label">Last name</label>
                            <input type="text" class="form-control"
                                   name="lastName" id="lastName" required>
                        </div>

                        <div class="col-12">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control"
                                   name="email" id="email" placeholder="you@example.com" required>
                        </div>

                        <div class="col-12">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control"
                                   name="address" id="address" placeholder="1234 Main St" required>
                        </div>

                        <div class="col-12">
                            <label for="phone" class="form-label">Phone</label>
                            <input type="text" class="form-control"
                                   name="phone" id="phone" placeholder="+380123456789" required>
                        </div>

                        <button class="btn btn-outline-primary btn-lg" type="submit">Send Confirmation Email</button>
                        <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal"
                                data-bs-target="#exampleModal">
                            Back To Toppings
                        </button>

                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Back To Toppings</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to come back? All changes will be erased, including
                                        toppings.
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No
                                        </button>
                                        <a href="toppings" type="button" class="btn btn-primary">Yes</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </main>

    <footer class="my-5 pt-5 text-body-secondary text-center text-small">
        <p class="mb-1">© 2024–2024 La pizza di Ilyusha</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Privacy</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>