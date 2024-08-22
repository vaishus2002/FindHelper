package com.app.controllers;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.CategoryIdDTO;
import com.app.dto.ServiceProviderDTO;
import com.app.dto.ServiceProviderDTONew;
import com.app.dto.ServiceProviderMDTO;
import com.app.dto.ServiceProviderResponceDto;
import com.app.dto.ServiceUpdateDto;
import com.app.dto.UserIdDTO;
import com.app.entity.CategoryEntity;
import com.app.entity.ServiceProvider;
import com.app.entity.User;
import com.app.service.CategoryService;
import com.app.service.ServiceProviderService;
import com.app.service.UserService;
@PermitAll
@RestController
@RequestMapping("/service-providers")
@CrossOrigin(origins = "http://localhost:5173")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    private ServiceProvider convertToEntity(ServiceProviderDTO dto) {
        ServiceProvider serviceProvider = modelMapper.map(dto, ServiceProvider.class);

        UserIdDTO userIdDTO = dto.getUser();
        if (userIdDTO != null && userIdDTO.getId() != null) {
            User user = userService.findById(userIdDTO.getId());
            serviceProvider.setUser(user);
        }

        CategoryIdDTO categoryIdDTO = dto.getCategory();
        if (categoryIdDTO != null && categoryIdDTO.getId() != null) {
            CategoryEntity category = categoryService.findById(categoryIdDTO.getId());
            serviceProvider.setCategory(category);
        }

        return serviceProvider;
    }

    private ServiceProviderDTO convertToDto(ServiceProvider serviceProvider) {
        return modelMapper.map(serviceProvider, ServiceProviderDTO.class);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @PostMapping
    public ResponseEntity<ServiceProviderDTO> createServiceProvider(@Valid @RequestBody ServiceProviderDTONew serviceProviderDTO) {
        ServiceProvider savedServiceProvider = serviceProviderService.save(serviceProviderDTO);
        return new ResponseEntity<>(convertToDto(savedServiceProvider), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getallProviders(@PathVariable Long categoryId) {
        return ResponseEntity.ok(serviceProviderService.getAllServiceProviders(categoryId));
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @GetMapping("/{id}/details")
    public ServiceProvider getMobileNumber(@PathVariable Long id) {
        return serviceProviderService.getMobileNumberByServiceProviderId(id);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @GetMapping("/byId/{id}")
    public ServiceProviderMDTO getServiceProvider(@PathVariable Long id) {
        return serviceProviderService.getServiceProviderDTOById(id);
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/exists/{userId}")
    public ResponseEntity<?> checkIfServiceProviderExists(@PathVariable Long userId) {
        boolean exists = serviceProviderService.existsByUserId(userId);
        return ResponseEntity.ok(exists);
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @GetMapping("/byUserId/{id}")
    public ResponseEntity<?> ServiceProviderExists(@PathVariable Long id) {
    	ServiceProviderResponceDto provider = serviceProviderService.findByUserIdforUpdate(id);
        return ResponseEntity.ok(provider);
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> ServiceProviderUpdate(@RequestBody ServiceUpdateDto sdto, @PathVariable Long id) {
        System.out.println("Updating ServiceProvider with ID: " + id);
        System.out.println("Received data: " + sdto);

        ServiceProvider provider = serviceProviderService.findByUserIdforPut(id, sdto);
        return ResponseEntity.ok(convertToDto(provider)); // Convert to DTO for response
    }

}