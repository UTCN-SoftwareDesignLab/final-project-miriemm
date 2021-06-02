package com.finalproject.payment;


import com.finalproject.payment.model.Payment;
import com.finalproject.payment.model.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PaymentMapper {


    @Mappings({
            @Mapping(target = "flightNr", source = "flight.flightNr"),
            @Mapping(target = "price", source = "flight.price")

    })
    PaymentDTO toDTO(Payment payment);
    Payment fromDto(PaymentDTO payment);
}
