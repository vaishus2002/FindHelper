package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.MapLocationDTO;
import com.app.service.MapLocationServiceInterface;

@RestController
@RequestMapping("/api/locations")
public class MapLocationController {

    @Autowired
    private MapLocationServiceInterface mapLocationService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MapLocationDTO> createMapLocation(@RequestBody MapLocationDTO mapLocationDTO) {
        MapLocationDTO createdLocation = mapLocationService.createMapLocation(mapLocationDTO);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MapLocationDTO> updateMapLocation(@PathVariable Long id, @RequestBody MapLocationDTO mapLocationDTO) {
        MapLocationDTO updatedLocation = mapLocationService.updateMapLocation(id, mapLocationDTO);
        if (updatedLocation != null) {
            return ResponseEntity.ok(updatedLocation);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @GetMapping("/{id}")
    public ResponseEntity<MapLocationDTO> getMapLocationById(@PathVariable Long id) {
        MapLocationDTO mapLocationDTO = mapLocationService.getMapLocationById(id);
        if (mapLocationDTO != null) {
            return ResponseEntity.ok(mapLocationDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @GetMapping
    public ResponseEntity<List<MapLocationDTO>> getAllMapLocations() {
        List<MapLocationDTO> mapLocations = mapLocationService.getAllMapLocations();
        return ResponseEntity.ok(mapLocations);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapLocation(@PathVariable Long id) {
        mapLocationService.deleteMapLocation(id);
        return ResponseEntity.noContent().build();
    }
}
