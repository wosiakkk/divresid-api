package com.ufpr.es.divresidapi.utils.cep;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/auth/cep")
public class ViaCepController {

	@GetMapping(value = "/{cep}")
	public ResponseEntity<AddressDTO> getCepData(
			@PathVariable(name = "cep") String cep){
		
		RestTemplate restTemplate =  new RestTemplate();
		String uri = "https://viacep.com.br/ws/"+cep+"/json";
		try {
			AddressDTO dto = restTemplate.getForObject(uri, AddressDTO.class);
			return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
