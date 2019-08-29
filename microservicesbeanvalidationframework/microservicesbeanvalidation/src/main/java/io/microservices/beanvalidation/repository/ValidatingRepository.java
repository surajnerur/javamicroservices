package io.microservices.beanvalidation.repository;

import io.microservices.beanvalidation.Input;
import org.springframework.data.repository.CrudRepository;

public interface ValidatingRepository extends CrudRepository<Input, Long> {
}
