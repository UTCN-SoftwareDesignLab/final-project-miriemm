import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allFlights() {
        return HTTP.get(BASE_URL + "/flight", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(flight) {
        return HTTP.post(BASE_URL + "/flight", flight, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(flight) {
        return HTTP.patch(BASE_URL + "/flight", flight, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(flight) {
        return HTTP.delete(BASE_URL + "/flight", {data: flight, headers: authHeader()}).then(
            (response) => {
                return response.data;
            });
    },
    findByMultipleCriteria(parameter1, parameter2, parameter3){
        return HTTP.get(BASE_URL + "/flight/findByMultipleCriteria/" + parameter1 + "/" + parameter2 + "/" +  parameter3, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};
