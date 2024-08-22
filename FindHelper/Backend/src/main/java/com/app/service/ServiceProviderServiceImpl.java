package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.ResourceNotFoundException;
import com.app.dto.ServiceProviderDTONew;
import com.app.dto.ServiceProviderMDTO;
import com.app.dto.ServiceProviderResponceDto;
import com.app.dto.ServiceUpdateDto;
import com.app.entity.CategoryEntity;
import com.app.entity.ServiceProvider;
import com.app.entity.User;
import com.app.repository.CategoryRepository;
import com.app.repository.ServiceProviderRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class ServiceProviderServiceImpl implements ServiceProviderService {

	@Autowired
	private ServiceProviderRepository serviceProviderRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository repo;
	@Autowired
	private CategoryRepository repoc;

	@Override
	public ServiceProvider save(ServiceProviderDTONew serviceProviderdto) {
		Long id = serviceProviderdto.getId();
		User u = repo.findById(id).orElse(null);
		Long cid = serviceProviderdto.getCategoryId();
		CategoryEntity ce = repoc.giveEntity(cid);
		System.out.println("cid=" + cid);
		ServiceProvider s = modelMapper.map(serviceProviderdto, ServiceProvider.class);
		s.setUser(u);
		s.setCategory(ce);
		return serviceProviderRepository.save(s);
	}

	@Override
	public List<ServiceProvider> getAllServiceProviders(Long id) {
		return serviceProviderRepository.getAllProviders(id);
	}

	@Override
	public ServiceProvider getMobileNumberByServiceProviderId(Long serviceProviderId) {
		return serviceProviderRepository.findById(serviceProviderId).orElseThrow(
				() -> new ResourceNotFoundException("ServiceProvider not found with id " + serviceProviderId));
	}

	@Override
	public ServiceProviderMDTO getServiceProviderDTOById(Long id) {
		ServiceProvider serviceProvider = serviceProviderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ServiceProvider not found with id " + id));

		ServiceProviderMDTO dto = modelMapper.map(serviceProvider, ServiceProviderMDTO.class);

		if (serviceProvider.getUser() != null) {
			dto.setMobileNumber(serviceProvider.getUser().getMobileNumber());
		}

		return dto;
	}

	@Override
	public ServiceProviderResponceDto findByUserIdforUpdate(Long userId) {
		ServiceProvider sProvider = serviceProviderRepository.findByUserIdLocal(userId);
		String cName = sProvider.getCategory().getCategoryName();
		ServiceProviderResponceDto sDTO = modelMapper.map(sProvider, ServiceProviderResponceDto.class);
		sDTO.setCategoryName(cName);

		return sDTO;
	}

	@Override
	public boolean existsByUserId(Long userId) {

		return serviceProviderRepository.existsByUserId(userId);
	}

	@Override
	public ServiceProvider findByUserIdforPut(Long id, ServiceUpdateDto dto) {
		ServiceProvider provider = serviceProviderRepository.findByUserIdLocal(id);
		if (provider == null) {
			throw new ResourceNotFoundException("ServiceProvider not found with id " + id);
		}
		modelMapper.map(dto, provider); // Ensure the mapping works
		System.out.println("Updated ServiceProvider: " + provider);
		return serviceProviderRepository.save(provider);
	}

}
