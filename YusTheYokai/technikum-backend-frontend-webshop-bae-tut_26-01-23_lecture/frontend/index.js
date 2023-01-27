$("#getProductsButton").on("click", function(e) {
    // $("#productsContainer").append("Hello World!");
    $.ajax({
        url: "http://localhost:8080/products/Lebensmittel",
        type: "GET",
        cors: true,
        success: function(products) { addProductsToPage(products) },
        error: function(error) { console.error(error) }
    })
});

function addProductsToPage(products) {
    const productsContainer = $("#productsContainer");
    productsContainer.empty();

    for (let product of products) {
        productsContainer.append(createProduct(product));
    }
}

function createProduct(product) {
    const name = $(`<h1>${product.name}</h1>`);
    const description = $(`<p>${product.description}</p>`);
    const image = $(`<img width="400" src="${product.imageUrl}">`);
    const price = $(`<p>${product.price}</p>`);

    const wrapper = $(`<div class="product"></div>`);
    wrapper.append(name);
    wrapper.append(description);
    wrapper.append(image);
    wrapper.append(price);

    return wrapper;
}
