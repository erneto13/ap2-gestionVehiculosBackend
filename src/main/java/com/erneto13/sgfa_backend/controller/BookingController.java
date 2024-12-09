package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.model.VehicleModel;
import com.erneto13.sgfa_backend.service.BookingService;
import com.erneto13.sgfa_backend.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final VehicleService vehicleService;

    public BookingController(BookingService bookingService, VehicleService vehicleService) {
        this.bookingService = bookingService;
        this.vehicleService = vehicleService;
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

    @GetMapping("/vehicle/{vehicleId}/bookings")
    public List<BookingModel> getBookingsForVehicle(@PathVariable Long vehicleId) {
        VehicleModel vehicle = vehicleService.getVehicleById(vehicleId);
        return bookingService.getBookingsForVehicle(vehicle);
    }

    @GetMapping("/driver/{name}")
    public ResponseEntity<List<BookingModel>> getBookingsByDriverName(@PathVariable String name) {
        List<BookingModel> bookings = bookingService.findBookingsByDriverNameExcludeCancelled(name);
        if (bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }


    @GetMapping("/ongoing")
    public List<BookingModel> getOnGoingBookings() {
        return bookingService.getOnGoingBookings();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingModel> updateBookingStatus(@PathVariable Long id, @RequestBody String status) {
        return bookingService.getBookingById(id)
                .map(existingBooking -> {
                    existingBooking.setStatus(status);
                    BookingModel updatedBooking = bookingService.saveBooking(existingBooking);
                    return ResponseEntity.ok(updatedBooking);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
