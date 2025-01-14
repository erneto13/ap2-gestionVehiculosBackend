package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.dto.RoutePoint;
import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RouteTrackingService {

    private final BookingRepository bookingRepository;

    private final ConcurrentHashMap<Long, List<RoutePoint>> bookingRoutes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Integer> currentRouteIndexes = new ConcurrentHashMap<>();

    @Autowired
    public RouteTrackingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void simulateRouteTracking(Long bookingId, List<double[]> routeCoordinates) {
        BookingModel booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        List<RoutePoint> routePoints = generateDetailedRoutePoints(routeCoordinates);

        // Almacena la ruta completa para este booking
        bookingRoutes.put(bookingId, routePoints);
        currentRouteIndexes.put(bookingId, 0);

        CompletableFuture.runAsync(() -> {
            while (currentRouteIndexes.get(bookingId) < routePoints.size() - 1) {
                int currentIndex = currentRouteIndexes.get(bookingId);
                RoutePoint nextPoint = routePoints.get(currentIndex + 1);

                // Simula movimiento actualizando el índice
                currentRouteIndexes.put(bookingId, currentIndex + 1);

                try {
                    Thread.sleep(5000); // Intervalo entre puntos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Limpia cuando se completa la ruta
            bookingRoutes.remove(bookingId);
            currentRouteIndexes.remove(bookingId);
        });
    }

    public RoutePoint getCurrentLocation(Long bookingId) {
        List<RoutePoint> route = bookingRoutes.get(bookingId);
        Integer currentIndex = currentRouteIndexes.get(bookingId);

        if (route != null && currentIndex != null) {
            return route.get(currentIndex);
        }
        return null;
    }

    private List<RoutePoint> generateDetailedRoutePoints(List<double[]> routeCoordinates) {
        List<RoutePoint> points = new ArrayList<>();

        for (double[] coordinate : routeCoordinates) {
            points.add(new RoutePoint(coordinate[1], coordinate[0])); // Lat, Lon
        }

        return points;
    }

    public void startRouteTracking(Long bookingId, List<double[]> routeCoordinates) {
        simulateRouteTracking(bookingId, routeCoordinates);
    }
}