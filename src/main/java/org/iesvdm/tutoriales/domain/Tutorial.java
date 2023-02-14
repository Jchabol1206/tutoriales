package org.iesvdm.tutoriales.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
@ToString
@Entity
@Builder
@Table(
    name = "Tutorials",
    schema = "appbbdd",
    indexes = {@Index(name = "title_index", columnList = "title", unique = true)}
)
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name= "title", nullable = false, length = 150)
    private String title;
    @Column(name="description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @OneToMany(mappedBy = "tutorial", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments;

}
