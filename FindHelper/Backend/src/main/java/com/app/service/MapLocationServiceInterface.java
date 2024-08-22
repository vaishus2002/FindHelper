package com.app.service;

import java.util.List;

import com.app.dto.MapLocationDTO;

public interface MapLocationServiceInterface {
	public MapLocationDTO createMapLocation(MapLocationDTO mapLocationDTO);

	public MapLocationDTO updateMapLocation(Long id, MapLocationDTO mapLocationDTO);

	public MapLocationDTO getMapLocationById(Long id);

	public List<MapLocationDTO> getAllMapLocations();

	public void deleteMapLocation(Long id);
}
