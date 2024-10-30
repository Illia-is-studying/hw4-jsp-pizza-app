<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Shipped</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card text-center p-4 shadow-sm" style="max-width: 400px;">
    <div class="card-body">
        <h3 class="card-title text-success">Your order has been shipped!</h3>
        <p class="card-text text-muted">Your order will be ready within 15 minutes.</p>
        <p class="card-text text-muted">If the readiness message has not arrived, please read this text again.</p>
        <a href="<%=request.getContextPath()%>" class="btn btn-primary">Make Another Order</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>