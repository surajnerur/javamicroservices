package io.microservices.beanvalidation.service;

import javax.validation.Valid;

import io.microservices.beanvalidation.Input;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class ValidatingService {

    void validateInput(@Valid Input input){
      // do something
    }

}
