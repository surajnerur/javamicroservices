package io.microservices.beanvalidation.controller.requestbody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.microservices.beanvalidation.InputWithCustomValidator;
import io.microservices.beanvalidation.service.ValidatingServiceWithGroups;

@RestController
public class ValidateRequestBodyCustomValidatorController {

	@Autowired
	ValidatingServiceWithGroups validatingServiceWithGroups;
	
  	@PostMapping("/validateBodyCustomValidator")
	public ResponseEntity<String> validateBody(@RequestBody InputWithCustomValidator input) {
		/*try {*/
			validatingServiceWithGroups.validateForCreate(input);
	      /*repository.save(input);
	      entityManager.flush();*/
	      return ResponseEntity.ok("valid");
		/*}catch(Exception e){
			e.printStackTrace();
		}*/
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
  	
  	@PostMapping("/validateBodyCustomValidatorUpdate")
	public ResponseEntity<String> validateBodyUpdate(@RequestBody InputWithCustomValidator input) {
		/*try {*/
			validatingServiceWithGroups.validateForUpdate(input);
	      /*repository.save(input);
	      entityManager.flush();*/
	      return ResponseEntity.ok("valid");
		/*}catch(Exception e){
			e.printStackTrace();
		}*/
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}  	
  	
  	@RequestMapping("/validateBodyCustomValidatorUpdate/{id}")
  	public InputWithCustomValidator getData(@PathVariable long id) {
  		return validatingServiceWithGroups.getData(id).get();
  	}
}
