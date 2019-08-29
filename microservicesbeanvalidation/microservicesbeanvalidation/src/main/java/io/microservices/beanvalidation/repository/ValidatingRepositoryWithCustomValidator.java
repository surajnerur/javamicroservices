package io.microservices.beanvalidation.repository;

import io.microservices.beanvalidation.InputWithCustomValidator;
import org.springframework.data.repository.CrudRepository;

public interface ValidatingRepositoryWithCustomValidator extends CrudRepository<InputWithCustomValidator, Long> {
}
