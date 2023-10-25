
$(document).ready(function() {

    $("#bestellButton").click(function () {
       window.alert("Vielen Dank für Ihre Bestellung!")
        });


    var localStorageToken = localStorage.getItem("localStorageToken");

    if (localStorageToken) {

        ///////// Den Token decodieren, um die Informationen zu erhalten //////////////////
        var tokenData = JSON.parse(atob(localStorageToken.split(".")[1]));
        var email = tokenData.e;                                    ////////speichere mir die email vom angemeldeten Benutzer ////////
        console.log("Benutzer-E-Mail: " + email);

        /////// Eine GET-Anfrage senden, um Benutzerdaten abzurufen des Benutzers der gerade angemeldet ist. ///////
        $.get('http://localhost:8080/user', function(users) {

            ///////eine filter methode um die Daten des Benutzer mit der email zu returnen welcher gerade angemeldet ist ///////
            var loggedInUser = $.grep(users, function(user) {
                return user.email === email;
            });


            ///// wenn loggedInUser Array mindestens 1 Element enthält gib mir den Benutzer mit dem Array eintrag an 0 Stelle und speicher ihn in die Variable user/////
            if (loggedInUser.length > 0) {
                // Auf das erste Element im Array zugreifen
                var user = loggedInUser[0];


                localStorage.setItem("UserID", user.id);  ////// speichern von der User ID die ich unterhalb und in der Detailpage wieder brauche ////

                $("#name").text(user.name);
                $("#email").text(user.email);
                $("#surname").text(user.surname);
                $("#country").text(user.country);
                $("#street").text(user.street);
                $("#postalcode").text(user.postalcode);
                $("#housenumber").text(user.housenumber);

            }
        });

        var userIDStr = localStorage.getItem("UserID");
        var userID = parseInt(userIDStr);
        console.log(userID);

        var cartId = localStorage.getItem("cart_id"); //// der Wert kommt von navbar.js ////
        console.log(cartId)

        /// brauche ich um mehrere Produkte im Einkaufswagen zu speichern ///
        var originals=[];

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/cart/" + cartId, ///// Get request um ein den Einkaufswagen zu bekommen des Benutzers der gerade angemeldet ist /////
            contentType: "application/json",
            success: function (response) {
                // Handle den Erfolgsfall hier
                console.log(response.originals);

                // gibt nur die daten von den einkaufswagen zurück die im array originals gepeichert sind
                originals = response.originals;
                var total=0;


                // Erstelle ein neues "cart-item" für jedes Produkt
                originals.forEach(function (object, index) {
                    var cartItem = $('<div class="cart-item"></div>');

                    // Füge den "Remove" Button mit dem eindeutigen Datenattribut hinzu
                    var removeButton = $('<div id="delete"><button class="btn remove-button" data-product-id="' + index + '">x</button></div>');

                    // Füge das Produktbild hinzu (falls verfügbar)

                    /* if (object.imageId) {
                        cartItem.append('<img src="' + object.imageId + '" alt="' + object.title + '">');
                    } */

                    //////jedes html element der klasse cartItem enthält die folgenden daten genau so oft wie die schleife davor ausführt //////
                    cartItem.append(removeButton);
                    cartItem.append('<div class="title-container">' + object.title + '</div>');
                    cartItem.append('<div class="price-container">' + object.category + '</div>');
                    cartItem.append('<div class="price-container">€' + object.price + '</div>');
                    // Fügt alle Cart items zur Liste der Produkte cart-items hinzu
                    $('.cart-items').append(cartItem);
                    total += object.price;  //////peis wird zusammengezählt
                });

                // Erstelle ein Element für die Gesamtsumme und füge es zum HTML hinzu  auf 2 Dezimalstellen
                var totalElement = $('<div class="cart-total">Total: €' + total.toFixed(2) + '</div>');

                $('.cart-items').append(totalElement); //////füge formatierte gegsamtsumme den cart-items hinzu

            },
            error: function (error) {
                // Handle den Fehlerfall hier
                console.error("Fehler beim GET Request:", error);


            },
        });


///////produkt mit dem index des buttons mit der product-id index wird gelöscht //////
        $('.cart-items').on('click', '.remove-button', function() {

            var index = $(this).data('product-id');
            originals.splice(index, 1);

            var currentDate = localStorage.getItem("currentDate");

            var requestData = {
                cart_id: cartId,
                userId: userID,
                originals: originals,
                date: currentDate,
            };

// Führe den PUT-Request durch, um die Änderungen am 'originals'-Array zu speichern//
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/cart/" + cartId,
                contentType: "application/json",
                data: JSON.stringify(requestData),
                success: function (response) {
                    console.log("originalsArray nach Entfernen: " + originals);
                    console.log("PUT Request erfolgreich");
                    localStorage.setItem("originals", JSON.stringify(originals));
                    location.reload();
                },
                error: function (error) {
                    // Handle den Fehlerfall hier
                    console.error("Fehler beim PUT Request:", error);
                },
            });


        });
    }
});
