package com.ufpr.es.divresidapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ufpr.es.divresidapi.converter.PropertyItemConverter;
import com.ufpr.es.divresidapi.dto.PropertyItemDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.PropertyItem;
import com.ufpr.es.divresidapi.model.PropertyItemImage;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.PropertyItemService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByProperty;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@RestController
@RequestMapping("api/auth/inventory")
public class PropertyItemController 
	extends PropertyItemBaseRestController<PropertyItem, 
		PropertyItemDTO, Property,User, Long> {

	@Autowired
	private PropertyItemService propertyItemService;
	@Autowired
	private LazyTableServiceByProperty<PropertyItem, Property> lazyTableService;
	
	
	
	@Override
	protected BaseResourceService<PropertyItemDTO, Long> 
		getBaseResourceService() {
		return this.propertyItemService;
	}

	@Override
	protected LazyTableServiceByProperty<PropertyItem, Property> 
		getPropertyLazyTableService() {
		return this.lazyTableService;
	}

	@Override
	protected LazyTableServiceByUser<PropertyItem, User> getLazyTableService() {
		return null;
	}
	
	
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/items/upload",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
		)
		@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<PropertyItemImage> upload(
				@RequestParam("file") MultipartFile file,
				@RequestParam("idItem") String id,
				@RequestParam("idImage") String idImage){
			
			try {
				String base64Image = Base64Utils.encodeToString(file.getBytes());
				PropertyItemImage image = new PropertyItemImage();
				image.setBase64Image(base64Image);
				
				if(Long.valueOf(idImage) != 0) {
					image.setId(Long.valueOf(idImage));
					return ResponseEntity.ok(this.propertyItemService
							.updateImage(image, Long.valueOf(id)));
				} else
					return ResponseEntity.ok(this.propertyItemService
							.saveImage(image, Long.valueOf(id)));
				
			} catch (IOException | NumberFormatException | ServiceException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
	
	@Override
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<PropertyItemDTO> update(@RequestBody PropertyItemDTO dto){
		try {
			return ResponseEntity.ok(this.propertyItemService.update(dto));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
