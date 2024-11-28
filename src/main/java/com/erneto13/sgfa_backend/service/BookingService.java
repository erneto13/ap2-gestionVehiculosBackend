package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<BookingModel> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<BookingModel> getBookingsByDriver(Long driver_id) {
        return bookingRepository.findByDriverId(driver_id);
    }

    public BookingModel saveBooking(BookingModel booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
