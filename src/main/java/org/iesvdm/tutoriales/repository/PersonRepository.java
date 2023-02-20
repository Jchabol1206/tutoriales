package org.iesvdm.tutoriales.repository;

import org.iesvdm.tutoriales.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
