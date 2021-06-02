package com.finalproject;

import com.finalproject.booking.BookingRepository;
import com.finalproject.flight.FlightRepository;
import com.finalproject.payment.PaymentRepository;
import com.finalproject.user.RoleRepository;
import com.finalproject.user.UserRepository;
import com.finalproject.security.AuthService;
import com.finalproject.security.dto.SignupRequest;
import com.finalproject.user.model.ERole;
import com.finalproject.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final FlightRepository flightRepository;

    private  final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;

    private final AuthService authService;


    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
            flightRepository.deleteAll();
            paymentRepository.deleteAll();
            bookingRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("alex@email.com")
                    .username("alex")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex1@email.com")
                    .username("alex1")
                    .password("WooHoo1!")
                    .roles(Set.of("EMPLOYEE"))
                    .build());
        }
    }
}
