package com.finalproject.report;

import com.finalproject.booking.BookingService;
import com.finalproject.booking.model.Booking;
import com.finalproject.payment.PaymentService;
import com.finalproject.payment.model.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PdfReportService implements ReportService {

    private final PaymentService paymentService;

    private final BookingService bookingService;

    @Override
    public byte[] export(Long bookingID) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PaymentDTO paymentDTO = new PaymentDTO();



        PDPageContentStream contentStream = null;
        try {
            PDImageXObject pdImage = PDImageXObject.createFromFile("D:\\Desktop\\New folder\\Airline System Project\\src\\assets\\images\\icon.jpg", document);

            contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.setLeading(30.0f);


            float yCordinate = page.getCropBox().getUpperRightY() - 30;
            float startX = page.getCropBox().getLowerLeftX() + 30;


            contentStream.beginText();
            contentStream.newLineAtOffset(startX, yCordinate);
            contentStream.showText("Boarding Pass");
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);


            contentStream.newLine();


            Booking foundBooking = bookingService.findById(bookingID);

            contentStream.showText("First name: " + foundBooking.getFirstName());
            contentStream.newLine();
            contentStream.showText("Last name: " + foundBooking.getLastName());
            contentStream.newLine();
            contentStream.showText("Flight Number: " + foundBooking.getFlightNr());
            contentStream.newLine();
            contentStream.showText("Price: " + foundBooking.getPrice());


            contentStream.endText();


            contentStream.drawImage(pdImage,30,10);

            contentStream.close();

            // PDF document to byte array:
            // https://stackoverflow.com/questions/11593116/using-pdfbox-how-do-i-retrieve-contents-of-pddocument-as-a-byte-array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();
            return byteArrayOutputStream.toByteArray();

           // document.save("outOfStock.pdf");
          //  document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ReportType getType() {
        return ReportType.PDF;
    }
}
