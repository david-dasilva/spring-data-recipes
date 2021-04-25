package net.daviddasilva.springdatarecipes;

import net.daviddasilva.springdatarecipes.domain.Account;
import net.daviddasilva.springdatarecipes.domain.Address;
import net.daviddasilva.springdatarecipes.domain.Customer;
import net.daviddasilva.springdatarecipes.persistence.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class SpringDataRecipesApplicationTests {

	@Autowired
	CustomerRepository repository;

	@Test
	void contextLoads() {
	}


	@Transactional
	@DisplayName("Saving a Customer should save relations too")
	@Test
	void savingCustomerShouldSaveRelationsToo() {
		// given
		Account account = new Account();
		account.setAccountName("nypd");

		Address address1 = new Address();
		address1.setStreetName("1 Police Plaza");
		Address address2 = new Address();
		address2.setStreetName("2 Nakatomi plaza");

		Customer customer = new Customer();
		customer.setAccount(account);
		customer.setAddresses(List.of(address1, address2));
		customer.setBirthday(Instant.now());
		customer.setEmail("john.mclane@mail.com");
		customer.setName("John McClane");

		account.setCustomer(customer);
		address1.setCustomer(customer);
		address2.setCustomer(customer);

		Customer savedCustomer = repository.saveAndFlush(customer);

		// when
		Customer actual = repository.getOne(savedCustomer.getId()); // getOne only work within a session. This is why the test is in a transaction

		// then
		then(actual.getAccount().getAccountName()).isEqualTo("nypd");
		then(actual.getAccount().getCustomer().getName()).isEqualTo("John McClane");
		then(actual.getAddresses()).hasSize(2);
		then(actual.getAddresses()).allMatch(address -> address.getCustomer().getEmail().equals("john.mclane@mail.com"));
	}
}
