package com.stl.crm.repository;

import org.springframework.data.repository.CrudRepository;
import com.stl.crm.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
