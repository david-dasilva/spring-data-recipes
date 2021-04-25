package net.daviddasilva.springdatarecipes.persistence;

import net.daviddasilva.springdatarecipes.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
