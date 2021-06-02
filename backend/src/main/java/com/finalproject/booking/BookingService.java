package com.finalproject.booking;

import com.finalproject.booking.model.Booking;
import com.finalproject.booking.model.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public Booking findById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found: " + id));
    }

    public BookingDTO create(BookingDTO booking) {
        return bookingMapper.toDto(bookingRepository.save(
                bookingMapper.fromDTO(booking)
        ));
    }
}
