var allProducts = []; // Initialisiere allProducts als leeres Array

$(document).ready(function() {
    var productContainer = $("#product-container");

    $.get('http://localhost:8080/original', function (products) {
        allProducts = products; // Speichere alle Produkte in allProducts

        // Zeige alle Produkte an
        displayProducts(products);
    });




    $("#all").click(function () {
        displayProducts(allProducts); // Zeige alle Produkte an
    });

////////////// FILTER ///////////////////

    $("#original").click(function () {
        var filteredProducts = $.grep(allProducts, function (product) {
            return product.category === 'ORIGINAL';
        });
        displayProducts(filteredProducts);
    });

    $("#sticker").click(function () {
        var filteredProducts = $.grep(allProducts, function (product) {
            return product.category === 'STICKER';
        });
        displayProducts(filteredProducts);
    });

    $("#print").click(function () {
        var filteredProducts = $.grep(allProducts, function (product) {
            return product.category === 'PRINT';
        });
        displayProducts(filteredProducts);
    });

    function displayProducts(products) {
        productContainer.empty();
        console.log("display products wird ausgef端ht1");

        // F端ge die Produkte dem jetzt leeren productContainer hinzu ...
        $.each(products, function(index, product) {
            console.log("display products wird ausgef端ht2");
            console.log(product);
            if (product) {
                console.log("imageId is valid")
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/images/' + product.imageId,
                    success: function (imageData) {
                        console.log("success!");
                        console.log(imageData.path);
                        if (imageData && imageData.path) {
                            var imageElement = $('<img src=".././Backend/uploads/' + imageData.path + '" class="nyx">');
                            var productHTML = `
                            <div class="col-lg-4 col-md-6 col-sm-12">
                            <a href="Detailpage.html?id=${product.id}">
                                ${imageElement.prop('outerHTML')} <!-- fuer das HTML -->
                                <div class="row imagetext">
                                    <div class="col-lg-4 col-md-4">${product.title}</div>
                                    <div class="col-lg-4 col-md-4">${product.category}</div>
                                    <div class="col-lg-4 col-md-4">${product.price}</div>
                                </div></a>
                            </div>
                        `;

                            productContainer.append(productHTML);
                        }
                    },
                    error: function (error) {
                        console.error('Error fetching image data:', error);
                    }
                });
            }
        });
    }
});
