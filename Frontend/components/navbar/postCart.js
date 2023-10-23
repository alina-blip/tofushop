function postCart() {
    //////////// AKtuelles datum auslesen ///////
    var currentDate = new Date().toISOString().split('T')[0];

    var userIDStr = localStorage.getItem("UserID");
    var userID = parseInt(userIDStr);


    console.log("userID :" + userID)


// Erstelle das JSON-Datenobjekt für deinen POST-Request
    var requestData = {
        "userId": userID,
        "originals": [],// Leeres Array
        "date": currentDate,
    };

// Führe den POST-Request durch
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cart',
        contentType: 'application/json',
        data: JSON.stringify(requestData),
        success: function (response) {
            var cartId = response.cart_id;
            console.log('cart_id: ', cartId);
            localStorage.setItem("cart_id", cartId);

            // Handle den Erfolgsfall hier
            console.log('POST Request erfolgreich');
            console.log("cartID::: ", cartId)
            window.location.replace("Warenkorb.html");
        },
        error: function (error) {
            // Handle den Fehlerfall hier
            console.error('Fehler beim POST Request:', error);
        }
    });
}
