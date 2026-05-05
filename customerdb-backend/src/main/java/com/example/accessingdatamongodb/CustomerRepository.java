package com.example.accessingdatamongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);

	@Query(value = "{}", fields = "{firstName : 1, _id : 0}")
	List<Customer> findNameAndExcludeId();

	@Aggregation("{ $group: { _id : '$lastName', names : { $addToSet : '$firstName' } } }")
	List<CustomerGroup> groupByLastnameAndFirstnames();

}
