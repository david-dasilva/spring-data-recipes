package net.daviddasilva.springdatarecipes.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // if db schema is already created with an autoincrement, use IDENTITY
    private Long id;

    private String accountName; // by default will be translated to account_name

    @OneToOne(mappedBy = "account") // There will be no customer_id column here since the relationship is owned by Customer
    private Customer customer;
}
