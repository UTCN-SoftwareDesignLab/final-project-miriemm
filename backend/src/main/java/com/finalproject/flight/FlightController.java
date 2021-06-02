package com.finalproject.flight;

import com.finalproject.UrlMapping;
import com.finalproject.flight.model.dto.FlightDTO;
//import com.assignment2.report.ReportServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.FLIGHT)
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;
//    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<FlightDTO> allFlights() {
        return flightService.findAll();
    }

    @PostMapping
    public FlightDTO create(@RequestBody FlightDTO flight) {
        return flightService.create(flight);
    }

    @PatchMapping
    public FlightDTO edit(@RequestBody FlightDTO flight) {
        return flightService.edit(flight);
    }

    @DeleteMapping
    public void delete(@RequestBody FlightDTO flight){
        flightService.delete(flight.getId());
    }

//    //    @GetMapping(UrlMapping.EXPORT_REPORT)
//    @GetMapping(value = "/export/{type}",
//            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public @ResponseBody byte[] export(@PathVariable ReportType type) {
//
//        return reportServiceFactory.getReportService(type).export();
//    }
//
//    @PatchMapping("/sellBook")
//    public void sellBook(@RequestBody BookDTO book) {
//        bookService.sellBook(book.getId());
//    }
//
    //@RequestMapping(path = "/findByMultipleCriteria/{departurePlace}/{destinationPlace}/{date}", method = RequestMethod.GET)
    @RequestMapping(path = "/findByMultipleCriteria/{departurePlace}/{destinationPlace}/{date}", method = RequestMethod.GET)
    public List<FlightDTO> findByMultipleCriteria(@PathVariable String departurePlace, @PathVariable String destinationPlace, @PathVariable String date) {
        return flightService.findByMultipleCriteria(departurePlace, destinationPlace, date);
    }

//
//    @RequestMapping(path = "/mno/objectKey/{departurePlace}/{destinationPlace}/{date}", method = RequestMethod.GET)
//    public List<FlightDTO> getBook(@PathVariable String departurePlace, @PathVariable String destinationPlace, @PathVariable String date) {
//        return flightService.findByMultipleCriteria(departurePlace,destinationPlace, date);
//    }

}
