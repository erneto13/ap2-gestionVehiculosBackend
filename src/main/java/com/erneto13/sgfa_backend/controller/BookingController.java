package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Obtener todas las reservas
    @GetMapping("bookings-list")
    public ResponseEntity<List<BookingModel>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingModel> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookingModel> createBooking(@RequestBody BookingModel booking) {
        return ResponseEntity.ok(bookingService.saveBooking(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingModel> updateBooking(@PathVariable Long id, @RequestBody BookingModel updatedBooking) {
        return bookingService.getBookingById(id)
                .map(existingBooking -> {
                    updatedBooking.setBookings_id(id);
                    return ResponseEntity.ok(bookingService.saveBooking(updatedBooking));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
