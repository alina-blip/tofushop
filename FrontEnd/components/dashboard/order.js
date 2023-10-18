$(document).ready(function () {
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

                ordersData.forEach(function (order) {
                    var orderDiv = $('<div class="cart"></div>');

                    orderDiv.append('<p>Rechnungsnummer: ' + order.id + '</p>');
                    orderDiv.append('<p>Kunde: ' + order.user.name + ' ' + order.user.surname + '</p>');

                    // produkte anzeigen
                    orderDiv.append('<img src="original.url" alt="product-picture">');
                    orderDiv.append('<p>Produkttitel: ' + order.original.title + '</p>');
                    orderDiv.append('<p>Größe: ' + order.original.size + '</p>');
                    orderDiv.append('<p>Material: ' + order.original.material + '</p>');
                    orderDiv.append('<p>Kategorie: ' + order.original.category + '</p>');
                    orderDiv.append('<p>Preis: € ' + order.original.price + '</p>');
                    orderDiv.append('<p>Größe: ' + order.original.size + '</p>');

                    orderDiv.append('<p>Total: ' + order.total + '</p>');
                    $('#ordersList').append(orderDiv);
                });
            }
        },
        error: function (xhr, status, error) {
            console.error('Error fetching orders:', xhr, status, error);
        },
    });
});
