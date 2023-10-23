//////////////////////////////////////////////////////////////
////////////// PRODUCTS /////////////////////
//////////////////////////////////////////////////////////////

$(document).ready(function () {

    //////////////////////////////////////////////////////////////
    ////////////// IMAGE UPLOAD /////////////////////
    //////////////////////////////////////////////////////////////

    $('#uploadImageBtn').click(function () {
        // Prepare form data for image upload
        var formData = new FormData();
        // Append the selected file to the FormData object with the key 'imageFile'
        formData.append('imageFile', $('#imageFile')[0].files[0]);
        // Log a message to indicate that the "Upload Image" button is clicked
        console.log("Upload Image Button is clicked.");

        // peform POST Request to upload the image
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/images',
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                // Handle the success response
                let imageId = response.id;
                $('#imageId').val(imageId);
                console.log('Image Upload Succesful');
                console.log(response.id);
                console.log(response);
                console.log("Image ID:", imageId);

            },
            error: function (error) {
                console.error('Error uploading image:', error);
                window.alert("Error uploading image");
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
            // Handle the success response
            console.log(response);
        },
        error: function (error) {
            console.error('Error downloading images:', error);
        }
    });


    //////////////////////////////////////////////////////////////
    ////////////// GET ALL PRODUCTS /////////////////////
    //////////////////////////////////////////////////////////////

    //original is synonym for  product
    $.ajax({
        url: 'http://localhost:8080/original',
        method: 'GET',
        dataType: 'json',
        success: function (productsData) {
            if (productsData.length === 0) {
                //if there are no products a message should be visible on the screen.
                $('#noProductsMessage').show();
            } else {
                console.log(productsData);
                //empty the productsList first to then show the correct list of products
                $('#productsList').empty();

                // Iterate through the list of products
                productsData.forEach(function (original) {
                    // Create productDiv, to display each original property in the productDiv
                    var productDiv = $('<div class="cart"></div>');
                    productDiv.append('<p class="id text-center">ID: ' + original.id + '</p');

                    // Handle image data - display it in a "div product-image", in the productDiv
                    var imageDiv = $('<div class="product-image"></div>');
                    productDiv.append(imageDiv);

                    // Initialize imageName
                    var imageName = '';

                    // Check if imageId is set correctly
                    if (original.imageId) {
                        console.log("imageId is valid")
                        // perform GET Request for EACH Image with the imageId
                        $.ajax({
                            type: 'GET',
                            url: 'http://localhost:8080/images/' + original.imageId,
                            success: function (imageData) {
                                if (imageData) {
                                    console.log("ImageId and imageDatapath is valid. Succesful Download.", imageData)
                                    //set imageName to the path of the image
                                    imageName = imageData.path;

                                    // Display the product image in the imageDiv
                                    imageDiv.append('<img src=".././Backend/uploads/' + imageData.path + '" height="250px" width="250px" style="border: 1px solid lightgrey">');
                                }
                            },
                            error: function (error) {
                                console.error('Error fetching image data:', error);
                            }
                        });
                    }
                    // Display Properties of a Product and make it a editable Form
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
        //prevent default browser actions
        event.preventDefault();

        //create a formData Object with the values of the submitted form
        var formData = {
            "id": 0,
            "title": $("#title").val(),
            "size": $("#size").val(),
            "material": $("#material").val(),
            "description": $("#description").val(),
            "price": $("#price").val(),
            "url": $("#url").val(),
            "category": $('[name="category"]:checked').val(),
            "quantity": $("#quantity").val(),
            "imageId": $("#imageId").val()
        };

        //Console Logs to ensure Data is correct
        console.log(formData);
        console.log(formData.imageId);

        //transformation to JSON Object
        var jsonString = JSON.stringify(formData);

        if (formData.imageId === "") {
            // Display an alert message if the image ID is empty
            window.alert("ImageId darf nicht leer sein. Press 'Upload Image' first before saving the product.");
        } else {
            //perform the POST method to upload the product, original is synonym for product
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/original',
                data: jsonString,
                contentType: 'application/json',
                success: function (response) {
                    console.log(response);
                    window.alert("Produkt erfolgreich hinzugefügt!!");
                    // reload page to make upload product visible
                    window.location.reload();
                },
                error: function (error) {
                    console.error(error);
                }
            });
        }
    });


    //////////////////////////////////////////////////////////////
    ////////////// EDIT PRODUCT /////////////////////
    //////////////////////////////////////////////////////////////

    // event handler for edit-button
    $('#productsList').on('click', '.edit-product', function () {
        var $productDiv = $(this).closest('.cart');
        //find the classes and make the read only false to edit the data
        $productDiv.find('.edit-url, .edit-title, .edit-size, .edit-material, .edit-category, .edit-price, .edit-quantity', '.edit-description').prop('readonly', false);
        //display the correct buttons for when editing the product
        $productDiv.find('.edit-product').hide();
        $productDiv.find('.save-changes').show();
    });

    // Event listener for the Save Changes - Button in the Div with ID productList
    $('#productsList').on('click', '.save-changes', function () {
        // Find the closest ancestor element with the class '.cart'
        var $productDiv = $(this).closest('.cart');
        // Now you can work with the $productDiv to make changes to the selected product or cart item...

        // Extract the product ID from the text content within the $productDiv element
        var productId = parseInt($productDiv.find('p:contains("ID:")').text().split(':')[1].trim(), 10);
        // The above code searches for a paragraph element within $productDiv that contains "ID:",
        // splits the text to extract the product ID, and converts it to an integer for further use.

        //new Data Object with the edited Data
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
        //Log edited Data
        console.log(editedData);
        // Transfrom edited Data to JSON Object
        var jsonStringEditedData = JSON.stringify(editedData);
        //Log jsonStringEditedData
        console.log(jsonStringEditedData);

        //Perform PUT request to save the changes of the specific Product
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/original/' + productId,
            data: jsonStringEditedData,
            contentType: 'application/json',
            success: function (response) {
                // Handle the success response
                console.log('Product updated:', response);

                // disable the other buttons and set readonly to true again
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
        // Find the closest ancestor element with the class '.cart'
        var $productDiv = $(this).closest('.cart');

        // Extract the product ID from the text content within the $productDiv element
        var productId = parseInt($productDiv.find('.id').text().split(':')[1].trim(), 10);
        // The above code searches for a paragraph element within $productDiv that contains "ID:",
        // splits the text to extract the product ID, and converts it to an integer for further use.

        //Confrim if you want to delete the product - THEN perform DELETE Request
        if (confirm('Are you sure you want to delete this product?')) {
            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/original/' + productId,
                success: function (response) {
                    console.log('Product deleted:', response);
                    window.alert("Product successfully deleted.")
                    $productDiv.remove();
                },
                error: function (error) {
                    console.error('Error deleting product:', error);
                }
            });
        }
    });
});
