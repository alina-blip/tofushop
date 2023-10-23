
$(document).ready(function () {
    // Die Funktion zum Extrahieren der Produkt-ID aus der URL
    function getProductIdFromURL() {
        var urlParams = new URLSearchParams(window.location.search);
        return urlParams.get("id"); // "Id" ist der Parametername in der URL
    }

    // Produkt-ID aus der URL extrahieren
    var productId = getProductIdFromURL();
    console.log("hier kommts noch hin: " + productId);

    $.get(
        `http://localhost:8080/original/${productId}`,
        function (productData) {
            var imgurl = $("#product-title").text(productData.title);
            $("#product-category").text(productData.category);
            $("#product-description").text(productData.description);
            $("#product-price").text(productData.price);
            $("#product-material").text(productData.material);
            $("#product-quantity").text(productData.quantity);
            $("#product-size").text(productData.size);
            $("#product-url").text(productData.url);
        }
    );

    var originals = [];

    // Benutze localStorage, um das 'originals' Array zu initialisieren, falls es bereits Daten enthält
    var storedOriginals = localStorage.getItem("originals");

    if (storedOriginals) {
        originals = JSON.parse(storedOriginals); //// um den wert von einemm Json zu einem Js objekt umzuwandeln
    }


    var currentDate = new Date().toISOString().split("T")[0];
    localStorage.setItem("currentDate", currentDate);

    //user ID speichern
    var userIDStr = localStorage.getItem("UserID");   //////ID kommt von Warenkorb.js file ////
    var userID = parseInt(userIDStr);
    console.log(userID);
    console.log("produktID:: " + productId);

    var cartId = localStorage.getItem("cart_id");
    console.log("cartID: " + cartId);

    // Erstelle das JSON-Datenobjekt für deinen PUT-Request
    var requestData = {
        cart_id: cartId,
        userId: userID,
        originals: originals,
        date: currentDate,
    };

    $("#addToBag").click(function () {
        console.log("requestDAta: ");
        console.log(requestData);

        var newItem = {
            id: productId,
        };

        localStorage.removeItem("originals");

        originals.push(newItem);

        // Aktualisiere localStorage, um die Änderungen am 'originals' Array zu speichern
        localStorage.setItem("originals", JSON.stringify(originals));

        // Führe den PUT-Request durch
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/cart/" + cartId, // Ersetze dies durch die tatsächliche URL
            contentType: "application/json",
            data: JSON.stringify(requestData),
            success: function (response) {
                // Handle den Erfolgsfall hier
                console.log("PUT Request erfolgreich");
            },
            error: function (error) {
                // Handle den Fehlerfall hier
                console.error("Fehler beim PUT Request:", error);
            },
        });
    });
});
