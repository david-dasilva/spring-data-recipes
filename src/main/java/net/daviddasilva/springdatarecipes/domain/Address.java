package net.daviddasilva.springdatarecipes.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // if db schema is already created with an autoincrement, use IDENTITY
    private Long id;

    private String streetName; // by default will be translated to "street_name"


    @ManyToOne
    @JoinColumn(name = "customer_id") // there will be an "customer_id" column in "address" pointing to "customer"
    private Customer customer;
}
