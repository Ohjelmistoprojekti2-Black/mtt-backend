package com.op2.op2.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.op2.op2.domain.Location;
import com.op2.op2.domain.LocationRepository;

@RestController
@CrossOrigin("http://localhost:5173")
public class LocationRestController {
    private final Logger log = LoggerFactory.getLogger(LocationRestController.class);

    @Autowired
    private LocationRepository locationRepo;

    //get all locations json
    @GetMapping({ "/locations" })
    public @ResponseBody List<Location> findAllLocations() {
        log.info("Fetch all locations");
        try {
            return (List<Location>) locationRepo.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No locations found");
        }
    }

    //get location by id json
    @GetMapping({ "/locations/{id}" })
    public @ResponseBody Optional<Location> getLocationById(@PathVariable("id") Long locationId) {
        log.info("Fetch location by id");
        try{
            return locationRepo.findById(locationId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a location");
        }
    }

    //save new location
    @PostMapping({ "/locations" })
    public @ResponseBody Location saveLocationRest(@RequestBody Location location) {
        return locationRepo.save(location);
    }

}
