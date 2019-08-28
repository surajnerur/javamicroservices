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
	
    @Validated(OnCreate.class)
    @Transactional
    public void validateForCreate(@Valid InputWithCustomValidator input){
		
	      repository.save(input);
	      entityManager.flush();
		
    }

    @Validated(OnUpdate.class)
    @Transactional
   public void validateForUpdate(@Valid InputWithCustomValidator input){
        // do something
	      repository.save(input);
	      entityManager.flush();    	
    }

    public Optional<InputWithCustomValidator> getData(long id) {
    	return repository.findById(id);
    }
}
