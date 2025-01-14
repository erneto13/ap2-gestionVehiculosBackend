package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.dto.RoutePoint;
import com.erneto13.sgfa_backend.service.RouteTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteController {

    @Autowired
    private RouteTrackingService routeTrackingService;

    @PostMapping("/start/{bookingId}")
    public ResponseEntity<String> startRouteTracking(
            @PathVariable Long bookingId,
            @RequestBody List<double[]> routeCoordinates
    ) {
        routeTrackingService.startRouteTracking(bookingId, routeCoordinates);
        return ResponseEntity.ok("Tracking started");
    }

    @GetMapping("/current-location/{bookingId}")
    public ResponseEntity<RoutePoint> getCurrentLocation(@PathVariable Long bookingId) {
        RoutePoint location = routeTrackingService.getCurrentLocation(bookingId);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }
}