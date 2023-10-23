$(document).ready(function () {
    // Make an AJAX request to fetch order data
    $.ajax({
        url: 'http://localhost:8080/cart',
        method: 'GET',
        dataType: 'json',
        success: function (ordersData) {
            console.log(ordersData);

            if (ordersData.length === 0) {
                $('#noOrdersMessage').show();
            } else {
                $('#ordersList').empty();

                // Iterate through the list of orders
                ordersData.forEach(function (order) {
                    var orderDiv = $('<div class="cart"></div>');

                    // Display order information
                    orderDiv.append('<p>Rechnungsnummer: ' + order.id + '</p>');
                    orderDiv.append('<p>Kunde: ' + order.user.name + ' ' + order.user.surname + '</p>');

                    // Display product information
                    orderDiv.append('<img src="' + order.original.url + '" alt="product-picture">');
                    orderDiv.append('<p>Produkttitel: ' + order.original.title + '</p>');
                    orderDiv.append('<p>Größe: ' + order.original.size + '</p>');
                    orderDiv.append('<p>Material: ' + order.original.material + '</p>');
                    orderDiv.append('<p>Kategorie: ' + order.original.category + '</p>');
                    orderDiv.append('<p>Preis: € ' + order.original.price + '</p>');
                    orderDiv.append('<p>Größe: ' + order.original.size + '</p>');

                    // Display the total price of the order
                    orderDiv.append('<p>Total: ' + order.total + '</p>');

                    // Append the order div to the ordersList container
                    $('#ordersList').append(orderDiv);
                });
            }
        },
        error: function (xhr, status, error) {
            console.error('Error fetching orders:', xhr, status, error);
        },
    });
});
