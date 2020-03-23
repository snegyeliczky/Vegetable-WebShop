export let dataHandler = {
    _api_post: function (url, data) {
        // it is not called from outside
        // sends the data to the API, and calls callback function

        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        }).then(r => r.ok)
    },

    _api_normalPost: function (url, data, callback) {
        // it is not called from outside
        // sends the data to the API, and calls callback function

        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        })
            .then(response => response.json())
            .then(json_response => callback(json_response));
    },

}