package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.model.ContactModel;
import com.erneto13.sgfa_backend.model.DriverModel;
import com.erneto13.sgfa_backend.model.VehicleModel;
import com.erneto13.sgfa_backend.repository.BookingRepository;
import com.erneto13.sgfa_backend.repository.DriverRepository;
import com.erneto13.sgfa_backend.repository.IContactsResository;
import com.erneto13.sgfa_backend.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final IContactsResository contactRepository;

    public BookingService(BookingRepository bookingRepository, VehicleRepository vehicleRepository, DriverRepository driverRepository, IContactsResository contactRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.contactRepository = contactRepository;
    }

    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<BookingModel> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public BookingModel saveBooking(BookingModel booking) {
        if (booking.getVehicle_id() != null) {
            VehicleModel vehicle = vehicleRepository.findById(booking.getVehicle_id())
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
            booking.setVehicle(vehicle);
        }

        if (booking.getDriverId() != null) {
            DriverModel driver = driverRepository.findById(booking.getDriverId())
                    .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
            booking.setDriver(driver);
        }

        if (booking.getContact_id() != null) {
            ContactModel contact = contactRepository.findById(booking.getContact_id())
                    .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
            booking.setContact(contact);
        }

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
