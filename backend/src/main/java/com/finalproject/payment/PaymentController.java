package com.finalproject.payment;


import com.finalproject.UrlMapping;
import com.finalproject.booking.BookingRepository;
import com.finalproject.booking.BookingService;
import com.finalproject.booking.model.Booking;
import com.finalproject.booking.model.dto.BookingDTO;
import com.finalproject.payment.model.dto.PaymentDTO;
import com.finalproject.report.ReportServiceFactory;
import com.finalproject.report.ReportType;
import com.finalproject.stripe.dto.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.PAYMENT)
@RequiredArgsConstructor
public class PaymentController {

   @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingService bookingService;

    private final BookingRepository bookingRepository;

    private final ReportServiceFactory reportServiceFactory;


    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody PaymentDTO checkout) throws StripeException {
        BookingDTO booking = new BookingDTO();
        booking.setFirstName(checkout.getFirstName());
        booking.setLastName(checkout.getLastName());
        booking.setFlightNr(checkout.getFlightNr());
        booking.setPrice(checkout.getPrice());
        bookingService.create(booking);

        Booking result = bookingRepository.findTopByOrderByIdDesc();

        Session session = paymentService.createSession(checkout, result.getId());

        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/export/{bookingID}/{type}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] export(@PathVariable Long bookingID, @PathVariable ReportType type) {

        return reportServiceFactory.getReportService(type).export(bookingID);
    }

}
