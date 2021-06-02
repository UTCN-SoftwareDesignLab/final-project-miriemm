package com.finalproject.booking;

import com.finalproject.booking.model.Booking;
import com.finalproject.booking.model.dto.BookingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO toDto(Booking booking);

    Booking fromDTO(BookingDTO bookingDTO);
}
