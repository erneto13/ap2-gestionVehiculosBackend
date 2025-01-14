package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.CompletedRoute;
import com.erneto13.sgfa_backend.model.FrequentClient;
import com.erneto13.sgfa_backend.repository.CompletedRouteRepository;
import com.erneto13.sgfa_backend.repository.FrequentClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/charts/")
public class UtilsController {

    @Autowired
    private CompletedRouteRepository completedRouteRepository;

    @Autowired
    private FrequentClientRepository frequentClientRepository;

    @GetMapping("completed-routes")
    public List<CompletedRoute> getAllCompletedRoutes() {
        return completedRouteRepository.findAll();
    }

    @GetMapping("/by-client")
    public List<CompletedRoute> getRoutesByClientId(@RequestParam Long clientId) {
        return completedRouteRepository.findByClientId(clientId);
    }

    @GetMapping("frequent-clients")
    public List<FrequentClient> getAllFrequentClients() {
        return frequentClientRepository.findAll();
    }

    @GetMapping("/top")
    public List<FrequentClient> getTopFrequentClients(@RequestParam Long clientId) {
        return frequentClientRepository.findByClientId(clientId);
    }
}
