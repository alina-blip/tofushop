//////////////////////////////////////////////////////////////
////////////// PRODUCTS /////////////////////
//////////////////////////////////////////////////////////////

$(document).ready(function () {

    //////////////////////////////////////////////////////////////
    ////////////// IMAGE UPLOAD /////////////////////
    //////////////////////////////////////////////////////////////


    $('#uploadImageBtn').click(function () {
        var formData = new FormData();
        formData.append('imageFile', $('#imageFile')[0].files[0]);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/images',
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                let imageId = response.id;
                $('#imageId').val(imageId);
                console.log('Image Upload Succesful');
                console.log(response.id);
                console.log(response);
                console.log("Image ID:", imageId);

            },
            error: function (error) {
                console.error('Error uploading image:', error);
            }
        });
    });

    //////////////////////////////////////////////////////////////
    ////////////// GET ALL IMAGES /////////////////////
    //////////////////////////////////////////////////////////////

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/images/all',
        success: function (response) {
            console.log(response);
            },
        error: function (error) {
            console.error('Error downloading images:', error);
        }
    });


    //////////////////////////////////////////////////////////////
    ////////////// GET ALL PRODUCTS /////////////////////
    //////////////////////////////////////////////////////////////



    $.ajax({
        url: 'http://localhost:8080/original',
        method: 'GET',
        dataType: 'json',
        success: function (productsData) {
            if (productsData.length === 0) {
                $('#noProductsMessage').show();
            } else {
                console.log(productsData);
                $('#productsList').empty();

                productsData.forEach(function (original) {
                    var productDiv = $('<div class="cart"></div>');
                    productDiv.append('<p class="id text-center">ID: ' + original.id + '</p');

                    var imageDiv = $('<div class="product-image"></div>');
                    productDiv.append(imageDiv);
                    var imageName ='';
                    if (original.imageId) {
                        console.log("imageId is valid")
                        $.ajax({
                            type: 'GET',
                            url: 'http://localhost:8080/images/' + original.imageId,
                            success: function (imageData) {
                                if (imageData) {
                                    console.log("imageId and imageDatapath is there and success")
                                    console.log(imageData);
                                    imageName = imageData.path;
                                    imageDiv.append('<img src="images/upload-images/' + imageData.path + '" height="250px" width="250px" style="border: 1px solid lightgrey">');
                                }
                            },
                            error: function (error) {
                                console.error('Error fetching image data:', error);
                            }
                        });
                    }
                    productDiv.append('<div class="form-group"><label>Bild_Id</label><input type="number" class="edit-title form-control" value="' + original.imageId + '" readonly></div>');
                    productDiv.append('<div class="form-group"><label>Titel</label><input type="text" class="edit-title form-control" value="' + original.title + '" readonly></div>');
                    productDiv.append('<div class="form-group"><label>Größe</label><input type="text" class="edit-size form-control" value="' + original.size + '" readonly></div>');
                    productDiv.append('<div class="form-group"><label>Material</label><input type="text" class="edit-material form-control" value="' + original.material + '" readonly></div>');
                    productDiv.append('<div class="form-group"><label>Description</label><input type="text" class="edit-description form-control" value="' + original.description + '" readonly></div>');
                    productDiv.append('<div class="form-group"><label>Kategorie</label><input type="text" class="edit-category form-control" value="' + original.category + '" readonly></div>');
                    productDiv.append('<div class="form-group"><label>Preis</label><input type="number" class="edit-price form-control" value="' + original.price + '" readonly></div>');
                    productDiv.append('<div class="form-group"><label>Stückzahl</label><input type="text" class="edit-quantity form-control" value="' + original.quantity + '" readonly></div>');
                    productDiv.append('<div class="register-container"><button class="btn btn-primary edit-product">Bearbeiten</button></div>');
                    productDiv.append('<div class="register-container"><button class="btn btn-danger delete-product">Löschen</button></div>');
                    productDiv.append('<div class="register-container"><button class="btn btn-success save-changes" style="display: none;">Änderungen speichern</button></div>');
                    $('#productsList').append(productDiv);
                });
            }
        },
        error: function (xhr, status, error) {
            console.error('Error fetching products:', xhr, status, error);
        },
    });


    //////////////////////////////////////////////////////////////
    ////////////// ADD NEW PRODUCT /////////////////////
    //////////////////////////////////////////////////////////////


    $('#productForm').submit(function (event) {
        event.preventDefault();

        var formData = {
            "id": 0,
            "title": $("#title").val(),
            "size": $("#size").val(),
            "material": $("#material").val(),
            "description": $("#description").val(),
            "price": $("#price").val(),
            "url": $("#url").val(),
            "category": $('[name="category"]').val(),
            "quantity": $("#quantity").val(),
            "imageId": $("#imageId").val()
        };

        console.log(formData);

        var jsonString = JSON.stringify(formData);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/original',
            data: jsonString,
            contentType: 'application/json',
            success: function (response) {
                console.log(response);
                window.alert("Produkt erfolgreich hinzugefügt!!");
            },
            error: function (error) {
                console.error(error);
            }
        });
    });



    //////////////////////////////////////////////////////////////
    ////////////// EDIT PRODUCT /////////////////////
    //////////////////////////////////////////////////////////////

    // event handler for edit-button
    $('#productsList').on('click', '.edit-product', function () {
        var $productDiv = $(this).closest('.cart');
        $productDiv.find('.edit-url, .edit-title, .edit-size, .edit-material, .edit-category, .edit-price, .edit-quantity', '.edit-description').prop('readonly', false);
        $productDiv.find('.edit-product').hide();
        $productDiv.find('.save-changes').show();
    });

    // event listener for the Save Changes - Button - with the productList ID to make it work
    $('#productsList').on('click', '.save-changes', function () {
        var $productDiv = $(this).closest('.cart');
        var productId = parseInt($productDiv.find('p:contains("ID:")').text().split(':')[1].trim(), 10);
        var editedData = {
            id: productId,
            url: $productDiv.find('.edit-url').val(),
            title: $productDiv.find('.edit-title').val(),
            size: $productDiv.find('.edit-size').val(),
            material: $productDiv.find('.edit-material').val(),
            category: $productDiv.find('.edit-category').val(),
            description: $productDiv.find('.edit-description').val(),
            price: parseFloat($productDiv.find('.edit-price').val()),
            quantity: parseInt($productDiv.find('.edit-quantity').val()),
            imageId: parseInt($productDiv.find('.edit-imageId').val()),
        };
        console.log(editedData);
        var jsonStringEditedData = JSON.stringify(editedData);
        console.log(jsonStringEditedData);


        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/original/' + productId,
            data: jsonStringEditedData,
            contentType: 'application/json',
            success: function (response) {
                // Handle the success response
                console.log('Product updated:', response);

                // disable the other buttons
                $productDiv.find('.edit-title, .edit-size, .edit-material, .edit-category, .edit-price, .edit-url, .edit-quantity, .edit-description').prop('readonly', true);
                $productDiv.find('.edit-product').show();
                $productDiv.find('.save-changes').hide();
            },
            error: function (error) {
                console.error('Error updating product:', error);
            }
        });
    });


    //////////////////////////////////////////////////////////////
    ////////////// DELETE PRODUCT /////////////////////
    //////////////////////////////////////////////////////////////


    $('#productsList').on('click', '.delete-product', function () {
        var $productDiv = $(this).closest('.cart');
        var productId = parseInt($productDiv.find('.id').text().split(':')[1].trim(), 10);

        if (confirm('Are you sure you want to delete this product?')) {
            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/original/' + productId,
                success: function (response) {
                    console.log('Product deleted:', response);
                    $productDiv.remove();
                },
                error: function (error) {
                    console.error('Error deleting product:', error);
                }
            });
        }
    });
});
