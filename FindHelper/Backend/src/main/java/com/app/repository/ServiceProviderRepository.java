package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.ServiceProvider;
import com.app.entity.User;
@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
@Query("select s from ServiceProvider s where s.category.id=:inputId")
List<ServiceProvider> getAllProviders(Long inputId);
User findUserById(Long id);
boolean existsByUserId(Long userId);
//ServiceProvider findByUserId(Long userId);
@Query(value = "SELECT * FROM service_provider WHERE user_id = :userId", nativeQuery = true)
ServiceProvider findByUserIdLocal(@Param("userId")Long userId);

}
