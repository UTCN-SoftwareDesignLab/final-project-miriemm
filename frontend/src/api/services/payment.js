import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    checkoutList(checkout) {
        return HTTP.post(BASE_URL + "/payment/create-checkout-session", checkout, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
};
