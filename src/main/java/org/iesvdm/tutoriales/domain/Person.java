package org.iesvdm.tutoriales.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    private String name;

    @ElementCollection
    @CollectionTable(name="person_addresses", joinColumns = @JoinColumn(name = "person_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "houseNumber", column = @Column(name= "house_number")),
            @AttributeOverride(name = "street", column = @Column(name="street")),
            @AttributeOverride(name = "city", column = @Column(name="city")),
            @AttributeOverride(name="zipCode", column = @Column(name = "zip_code"))
    })
    private Set<Address> secondaryAddresses = new HashSet<>();
    @Embedded
    private Address mainAddress;


    @ElementCollection
    @CollectionTable(name = "person_phone_number", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name= "phone_number")
    private Set<String> phoneNumbers;




}

