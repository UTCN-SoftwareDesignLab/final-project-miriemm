import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    pdfReport(bookingID){
        return HTTP.get(BASE_URL + "/payment/export/" + bookingID +"/PDF", { headers: authHeader(), responseType: 'blob' }).then(
            (response) => {
                return response.data;
            }
        );
    },
};