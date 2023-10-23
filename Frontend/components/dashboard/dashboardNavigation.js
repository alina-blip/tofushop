$(document).ready(function () {
    showUsers();
    function showUsers() {
        $('#userList').show();
        $('#ordersList').hide();
        $('#productsList').hide();
        $('#addNewProduct').hide();
        $('.cart-container h2').text('Registrierte Benutzer');
    }
    function showOrders() {
        $('#userList').hide();
        $('#ordersList').show();
        $('#productsList').hide();
        $('#addNewProduct').hide();
        $('.cart-container h2').text('Bestellungen');

    }
    function showProducts() {
        $('#userList').hide();
        $('#ordersList').hide();
        $('#productsList').show();
        $('#addNewProduct').hide();
        $('.cart-container h2').text('Produkte');

    }
    function showNewProductForm() {
        $('#userList').hide();
        $('#ordersList').hide();
        $('#productsList').hide();
        $('#addNewProduct').show();
        $('.cart-container h2').text('Produkt hinzufügen');
    }
    $('#showUsersButton').click(showUsers);
    $('#showOrdersButton').click(showOrders);
    $('#showProductsButton').click(showProducts);
    $('#showNewProductButton').click(showNewProductForm);

});
