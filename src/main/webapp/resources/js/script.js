function addToCart(pizzaId, pizzaPrice) {
    fetch("cart", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({pizzaId: pizzaId})
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                let cartPanel = document.getElementById("cartPanel")
                cartPanel.classList.remove("hidden");
                cartPanel.classList.add("d-flex");

                const totalPizzas = document.getElementById("totalPizzas");
                let count = parseInt(totalPizzas.textContent.trim());
                if(isNaN(count)) {
                    count = 0;
                }
                count += 1;
                totalPizzas.textContent = count.toString();

                const totalPrice = document.getElementById("totalPrice");
                let price = parseFloat(totalPrice.textContent.trim());
                if(isNaN(price)) {
                    price = 0.0;
                }
                price += parseFloat(pizzaPrice);
                totalPrice.textContent = price.toFixed(2);
            }
        })
        .catch(error => console.log('Error:', error));
}

function changePrice(checkbox, changeableElementName, newPrice) {
    const totalPrice = document.getElementById("totalPrice");
    const elementPrice = document.getElementById(changeableElementName);

    let totalPriceFloat = parseFloat(totalPrice.textContent.trim());
    let elementPriceFloat = parseFloat(elementPrice.textContent.trim());

    if(checkbox.checked) {
        totalPriceFloat += parseFloat(newPrice);
        elementPriceFloat += parseFloat(newPrice);
    } else {
        totalPriceFloat -= parseFloat(newPrice);
        elementPriceFloat -= parseFloat(newPrice);
    }

    totalPrice.textContent = totalPriceFloat.toFixed(2);
    elementPrice.textContent = elementPriceFloat.toFixed(2);
}