package org.iesvdm.tutoriales.domain;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(
        name = "socio",
        schema ="bbdd_tutoriales"/*,*/
      //  indexes = {@Index(name = "socio_index", columnList = "dni", unique = true)}
)
public class Socio {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="id_socio")
    private long id;
    @Column(name= "dni", nullable = false, length = 150)
    private String dni;
    @Column(name= "nombre", nullable = false, length = 150)
    private String nombre;
    @Column(name= "apellidos", nullable = false, length = 150)
    private String apellidos;

    @OneToOne(mappedBy = "socio", /*fetch = FetchType.EAGER,*/ cascade = CascadeType.ALL)
    private Tarjeta tarjeta;
}
