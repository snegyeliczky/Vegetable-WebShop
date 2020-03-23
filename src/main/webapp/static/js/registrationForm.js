export let registrationForm;
import {dataHandler} from "./dataHandler.js";
registrationForm = {
    registrationForm: function () {
        const signUp = document.querySelector("#sign-up");

        signUp.addEventListener('click', function () {
            let name = document.querySelector("#orangeForm-name").value;
            let email = document.querySelector("#orangeForm-email").value;
            let password = document.querySelector("#orangeForm-pass").value;

            let text = {"name":name, "email": email, "password": password};
            dataHandler._api_post("/registration", text);
            const modalBody = document.querySelector(".modal-body");
            const modalFooter = document.querySelector(".modal-footer");
            modalBody.innerHTML = "<h2>Thank you for your registration</h2>";
            modalFooter.innerHTML = "";
        })



    }
};