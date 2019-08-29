package io.microservices.beanvalidation.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import io.microservices.beanvalidation.InputWithCustomValidator;
import io.microservices.beanvalidation.repository.ValidatingRepositoryWithCustomValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional; This is also working
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ValidatingServiceWithGroups {

	@Autowired
	private ValidatingRepositoryWithCustomValidator repository;

	@Autowired
	private EntityManager entityManager;
	
    //@Validated /* set this if there is common validation*/
    @Validated(OnCreate.class) /* set this if there is validation is to be performed on group*/
    //@Transactional /*This is required if entity manager is used else we will get error*/
    public void validateForCreate(@Valid InputWithCustomValidator input){
		
	      /*repository.save(input);
	      entityManager.flush();*/
		/*No need to do anything here if we just want to validate and forward the request to appropriate service.*/
    }

    //@Validated /* set this if there is common validation*/
    @Validated(OnUpdate.class)  /* set this if there is validation is to be performed on group*/
    //@Transactional /*This is required if entity manager is used else we will get error*/
   public void validateForUpdate(@Valid InputWithCustomValidator input){
        // do something
	      /*repository.save(input);
	      entityManager.flush();*/    	
    	/*No need to do anything here if we just want to validate and forward the request to appropriate service.*/
    }

    public Optional<InputWithCustomValidator> getData(long id) {
    	return repository.findById(id);
    }
}
