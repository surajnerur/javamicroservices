package io.microservices.beanvalidation.service;

import javax.validation.Valid;

import io.microservices.beanvalidation.InputWithCustomValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class ValidatingServiceWithGroups {

    @Validated(OnCreate.class)
    void validateForCreate(@Valid InputWithCustomValidator input){
      // do something
    }

    @Validated(OnUpdate.class)
    void validateForUpdate(@Valid InputWithCustomValidator input){
        // do something
    }

}
