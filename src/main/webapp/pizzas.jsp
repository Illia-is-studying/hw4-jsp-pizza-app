<%@ page import="java.util.List" %>
<%@ page import="com.example.hw4jsppizzaapp.Models.ViewModels.PizzaViewModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<PizzaViewModel> pizzas = (List<PizzaViewModel>) request.getAttribute("pizzas");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizzas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%--    <link rel="stylesheet" href="css/style.css">--%>
    <style>
        .pizza-container {
            width: 75%;
            margin: 20px auto;
            position: relative;
        }

        .pizza-base {
            width: 100%;
            padding-bottom: 100%;
            background-color: #F4A460;
            border-radius: 50%;
            position: relative;
        }

        .pizza-edge {
            width: 85%;
            padding-bottom: 85%;
            background-color: #FF6347;
            border-radius: 50%;
            position: absolute;
            top: 7.5%;
            left: 7.5%;
        }

        .cheese {
            width: 80%;
            padding-bottom: 80%;
            background-color: #FFD700;
            border-radius: 50%;
            position: absolute;
            top: 10%;
            left: 10%;
        }

        .basil {
            width: 15%;
            padding-bottom: 10%;
            background-color: #228B22;
            position: absolute;
            border-radius: 50%;
        }

        .ham {
            width: 15%;
            height: 7%;
            background-color: #ff8b9e;
            position: absolute;
            border-radius: 10px;
            top: 30%;
            left: 35%;
            transform: rotate(45deg);
        }

        .pineapple {
            width: 8%;
            height: 8%;
            background-color: #FFFF66;
            border-radius: 20% 20% 10% 10%;
            position: absolute;
        }

        .cheese-spot {
            width: 20%;
            height: 15%;
            border-radius: 50%;
            position: absolute;
        }
        .gorgonzola { background-color: #5F9EA0; }
        .parmesan { background-color: #FFFACD; }
        .ricotta { background-color: #FFFFFF; }

        .olives {
            width: 6%;
            height: 6%;
            background-color: black;
            border-radius: 50%;
            position: absolute;
            top: 45%;
            left: 45%;
        }
        .olives::before {
            content: "";
            display: block;
            width: 50%;
            height: 50%;
            background-color: #FFD700;
            border-radius: 50%;
            position: absolute;
            top: 25%;
            left: 25%;
        }

        .artichoke {
            width: 10%;
            height: 12%;
            background-color: #98FB98;
            clip-path: polygon(50% 0%, 10% 100%, 90% 100%);
            position: absolute;
            top: 60%;
            left: 40%;
            border-radius: 5px;
        }

        .mushroom {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 10%;
            height: 15%;
            position: absolute;
        }
        .mushroom .cap {
            width: 100%;
            height: 50%;
            background-color: #aa9988;
            border-radius: 50% 50% 0 0;
            position: relative;
        }
        .mushroom .stem {
            width: 40%;
            height: 50%;
            background-color: #aa9988;
            border-radius: 0 0 20% 20%;
            margin-top: -5%;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col d-flex justify-content-center">
            <% for (PizzaViewModel pizza : pizzas) {
                request.setAttribute("pizza", pizza);
            %>
            <jsp:include page="pizza-card.jsp"></jsp:include>
            <% } %>
            <div class="card mx-2" style="width: 18rem;">
                <div class="d-grid text-center">
                    <a href="#" class="btn btn-outline-warning border border-0">
                        <svg xmlns="http://www.w3.org/2000/svg" width="92%" height="92%" fill="currentColor"
                             class="bi bi-question-circle" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                            <path
                                    d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286m1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94"/>
                        </svg>
                    </a>
                </div>
                <div class="text-center card-body">
                    <h5 class="card-title">Pizza Constructor</h5>
                    <p class="card-text">Don't fit our pizza recipes? Create your own recipe with our pizza builder.
                    </p>
                    <a href="#" class="btn btn-outline-primary">
                        Create Pizza
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>