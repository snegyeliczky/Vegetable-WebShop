import {registrationForm} from "./registrationForm.js";

export let init;
init = {


    init: function () {
        const checkoutButton = document.querySelector("#start-payment");
        const payPall = document.querySelector("#payPal");
        const creditCard = document.querySelector("#creditCard");
        const register = document.querySelector("#registerButton");

        if(checkoutButton){
        checkoutButton.addEventListener("click", function () {
            init._api_get("/paymentForm", function (response) {
                document.querySelector(".container").innerHTML = response;
            })
        })}

        register.addEventListener("click", function () {
            document.querySelector("#modalRegisterForm").setAttribute("ariaHidden", "true");
            registrationForm.registrationForm();
        });

        if(payPall){
            payPall.addEventListener("click", function () {
                document.querySelector(".paymentContainer").innerHTML = "<h2> Thank you for your order you choose the payPall method </h2>"
            })
        }

        if (creditCard){
            creditCard.addEventListener("click", function () {
                document.querySelector(".paymentContainer").innerHTML = "<h2> Thank you for your order you choose the Credit Card method </h2>"
            })
        }



    },




    _api_get: function (url, callback) {
        // it is not called from outside
        // loads data from API, parses it and calls the callback with it

        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
            .then(response => response.json())  // parse the response as JSON
            .then(json_response => callback(json_response));  // Call the `callback` with the returned object
    },

};