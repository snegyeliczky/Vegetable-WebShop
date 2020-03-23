import {dataHandler} from "./dataHandler.js";

function addToCartAnimation() {
    $('.add-to-card').on('click', function () {
        let cart = $('#basket');
        let imgToDrag = $(this).parents('.card').find('img').eq(0);
        if (imgToDrag) {
            let imgClone = imgToDrag.clone()
                .offset({
                    top: imgToDrag.offset().top,
                    left: imgToDrag.offset().left
                })
                .css({
                    'opacity': '0.8',
                    'border-radius':'35px',
                    'position': 'absolute',
                    'height': '150px',
                    'width': '150px',
                    'z-index': '5000'
                })
                .appendTo($('body'))
                .animate({
                    'top': cart.offset().top + 10,
                    'left': cart.offset().left + 10,
                    'width': 75,
                    'height': 75
                }, 2000);

            imgClone.animate({
                'width': 0,
                'height': 0
            }, function () {
                $(this).detach()
            });
        }
    });
}

function AddToCart() {
    let addButtons = document.querySelectorAll(".add-to-card");
    for (let button of addButtons) {
        button.addEventListener("click", function () {
            let productID = button.dataset.productid;
            let data = {"prodId": productID};
            console.log(data);
            console.log(productID);
            dataHandler._api_post("/addToCart", data, () => {
                console.log("ok");
            })
        })
    }
}

function main() {
    AddToCart();
    addToCartAnimation();
}

main();

