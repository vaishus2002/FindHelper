package com.app.repository;

import com.app.entity.MapLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapLocationRepository extends JpaRepository<MapLocation, Long> {
}
