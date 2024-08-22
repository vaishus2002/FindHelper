package com.app.service;

import java.util.List;

import com.app.dto.ServiceProviderDTONew;
import com.app.dto.ServiceProviderMDTO;
import com.app.dto.ServiceProviderResponceDto;
import com.app.dto.ServiceUpdateDto;
import com.app.entity.ServiceProvider;

public interface ServiceProviderService {
	public ServiceProvider save(ServiceProviderDTONew serviceProvider);
	public List<ServiceProvider>getAllServiceProviders(Long id);
	public ServiceProvider getMobileNumberByServiceProviderId(Long serviceProviderId);
	public ServiceProviderMDTO getServiceProviderDTOById(Long id);
	public boolean existsByUserId(Long userId);
	//public ServiceProvider existsByUserId(Long userId);
	public ServiceProviderResponceDto findByUserIdforUpdate(Long userId);
	public ServiceProvider findByUserIdforPut(Long id, ServiceUpdateDto sdto); 
}
