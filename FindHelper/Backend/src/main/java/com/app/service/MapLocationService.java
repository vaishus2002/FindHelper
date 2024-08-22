package com.app.service;

import com.app.dto.MapLocationDTO;
import com.app.entity.MapLocation;
import com.app.repository.MapLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MapLocationService implements MapLocationServiceInterface {

	@Autowired
	private MapLocationRepository mapLocationRepository;

	@Override
	public MapLocationDTO createMapLocation(MapLocationDTO mapLocationDTO) {
		MapLocation mapLocation = new MapLocation();
		mapLocation.setLocation(mapLocationDTO.getLocation());
		mapLocation = mapLocationRepository.save(mapLocation);
		return new MapLocationDTO(mapLocation.getId(), mapLocation.getLocation());
	}

	@Override
	public MapLocationDTO updateMapLocation(Long id, MapLocationDTO mapLocationDTO) {
		Optional<MapLocation> existingLocation = mapLocationRepository.findById(id);
		if (existingLocation.isPresent()) {
			MapLocation mapLocation = existingLocation.get();
			mapLocation.setLocation(mapLocationDTO.getLocation());
			mapLocation = mapLocationRepository.save(mapLocation);
			return new MapLocationDTO(mapLocation.getId(), mapLocation.getLocation());
		}
		return null; // Or throw a custom exception
	}

	@Override
	public MapLocationDTO getMapLocationById(Long id) {
		Optional<MapLocation> mapLocation = mapLocationRepository.findById(id);
		return mapLocation.map(loc -> new MapLocationDTO(loc.getId(), loc.getLocation())).orElse(null);
	}

	@Override
	public List<MapLocationDTO> getAllMapLocations() {
		return mapLocationRepository.findAll().stream().map(loc -> new MapLocationDTO(loc.getId(), loc.getLocation()))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteMapLocation(Long id) {
		mapLocationRepository.deleteById(id);
	}
}
