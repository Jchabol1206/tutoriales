package org.iesvdm.tutoriales;

import jakarta.transaction.Transactional;
import org.iesvdm.tutoriales.domain.*;
import org.iesvdm.tutoriales.repository.PersonRepository;
import org.iesvdm.tutoriales.repository.SocioRepository;
import org.iesvdm.tutoriales.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;


@SpringBootTest
class TutorialesApplicationTests {
    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    SocioRepository socioRepository;
    @Autowired
    PersonRepository personRepository;

    @Test
    void testTutorialRepository(){
        Tutorial tutorial1 = Tutorial.builder().title("Titulo tutorial 1").description("prueba").build();
        tutorialRepository.save(tutorial1);
        List<Tutorial> tutorialList = tutorialRepository.findAll();
        tutorialList.forEach(System.out::println);
    }
    @Test
    void contextLoads() {
    }
    @Test
    void testTutorialWithCommentsRepository() {

        Comment comment1 = Comment.builder().content("El tutorial no está mal, pero hay cosillas que no cuenta").build();
        Comment comment2 = Comment.builder().content("Genial!").build();

        List<Comment> commentList = Arrays.asList(comment1, comment2);

//Anotacion Lombok @Builder
        Tutorial tutorial1 = Tutorial.builder().title("Tuto2 JPA")
                .description("Otro tuto sobre modelo/entidad con JPA/Hibernate")
                .build();

        Tutorial tutorialSave = tutorialRepository.save(tutorial1);
//Alternativa tutorialSave


//Alternativa tutorialFnd recargando la entidad para tener la colección Comments creada
//Tutorial tutorialFind = tutorialRepository.findById(tutorialSave.getId()).get();

//Seteamos el tutorial
        commentList.forEach( c -> c.setTutorial(tutorialSave));
//commentList.forEach( c -> c.setTutorial(tutorialFind));

        tutorialSave.setComments(commentList);
//tutorialFind.getComments().addAll(commentList);

// Segunda actualización para actualizar las FK id_tutorial de comentarios nuevos.

        tutorialRepository.save(tutorialSave);
//tutorialRepository.save(tutorialFind);

        List<Tutorial> tutorialList = tutorialRepository.findAll();
        tutorialList.forEach(System.out::println);

    }

    @Test
    void testSocioRepository(){

        Tarjeta tarjeta = Tarjeta.builder().numero("52277497866899355").caducidad("04/27").build();


        Socio socio = Socio.builder().dni("00120011X")
                .nombre("Pedro").apellidos("Picapiedra Garcia").tarjeta(tarjeta).build();

        tarjeta.setSocio(socio);

        socioRepository.save(socio);

        List<Socio> socioList = socioRepository.findAll();

        for(Socio socio12 : socioList){
            System.out.println(socio12.toString());
        }
        socioRepository.delete(socio);
    }


    @Test
    @Commit
    @Transactional
    public void personElementCollectionStringAndAddressEmbeddable(){
        Person person = Person.builder().name("Pablo Marmol")
                .phoneNumbers(new HashSet<>())
                .secondaryAddresses(new HashSet<>())
                .build();

        Address mainAddress = Address.builder().street("Belgica")
                .city("Malaga").zipCode(29402).build();

        Address address1 = Address.builder().street("Portugal")
                .city("Malaga").zipCode(29403).build();

        Address address2 = Address.builder().street("Francia").city("Malaga")
                .zipCode(29043).build();

        person.setMainAddress(mainAddress);
        person.getSecondaryAddresses().add(address1);
        person.getSecondaryAddresses().add(address2);
        person.getPhoneNumbers().add("952132439");
        person.getPhoneNumbers().add("955461238");
        personRepository.save(person);

        Person personSaved = personRepository.findById(person.getId()).get();
        System.out.println(personSaved.toString());
        System.out.println(person.toString());
      //  assertThat(person.getAddresses(),personSaved.getAddresses()).hasSize(2);
        //assertThat(personSaved.getPhoneNumbers()).hasSize(2);
    }



}
