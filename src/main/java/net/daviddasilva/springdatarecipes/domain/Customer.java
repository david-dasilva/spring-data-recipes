package net.daviddasilva.springdatarecipes.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

/**
 * A customer can have multiples addresses but only one account is linked to it
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // if db schema is already created with an autoincrement, use IDENTITY
    private Long id;

    private String name;

    private String email;

    private Instant birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id") // 'account_id' is the column in 'customer' pointing to the column 'id' of 'account'
    private Account account;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // this makes the relationship bi-directional, owned by customer
    private List<Address> addresses;
}
