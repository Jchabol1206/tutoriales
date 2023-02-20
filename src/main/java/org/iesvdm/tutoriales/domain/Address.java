package org.iesvdm.tutoriales.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
@Builder
public class Address {

    private long id;

    private String street;

    private String city;

    private int zipCode;
}
