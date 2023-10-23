$(document).ready(function () {
    // Function to display the list of registered users
    function showUsers() {
        $('#userList').show();
        $('#ordersList').hide();
        $('#productsList').hide();
        $('#addNewProduct').hide();
        $('.cart-container h2').text('Registrierte Benutzer');
    }

    // Function to display the list of orders
    function showOrders() {
        $('#userList').hide();
        $('#ordersList').show();
        $('#productsList').hide();
        $('#addNewProduct').hide();
        $('.cart-container h2').text('Bestellungen');
    }

    // Function to display the list of products
    function showProducts() {
        $('#userList').hide();
        $('#ordersList').hide();
        $('#productsList').show();
        $('#addNewProduct').hide();
        $('.cart-container h2').text('Produkte');
    }

    // Function to show the form for adding a new product
    function showNewProductForm() {
        $('#userList').hide();
        $('#ordersList').hide();
        $('#productsList').hide();
        $('#addNewProduct').show();
        $('.cart-container h2').text('Produkt hinzufügen');
    }

    // Click event handlers for navigation buttons
    $('#showUsersButton').click(showUsers);
    $('#showOrdersButton').click(showOrders);
    $('#showProductsButton').click(showProducts);
    $('#showNewProductButton').click(showNewProductForm);

    // Initial display of users
    showUsers();
});
