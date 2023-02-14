package org.iesvdm.tutoriales.repository;

import org.iesvdm.tutoriales.domain.Socio;
import org.iesvdm.tutoriales.domain.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Long> {
}
