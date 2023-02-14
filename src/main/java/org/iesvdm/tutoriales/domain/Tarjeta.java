package org.iesvdm.tutoriales.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Tarjeta {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tarjeta")
    private long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "caducidad")
    private String caducidad;

    @OneToOne(/*fetch = FetchType.LAZY,*/ optional = false)
    @JoinColumn(name = "id_tarjeta", nullable=false, foreignKey = @ForeignKey(name="FK_socio"))
    @MapsId
    @JsonIgnore
    @ToString.Exclude
    private Socio socio;
}
