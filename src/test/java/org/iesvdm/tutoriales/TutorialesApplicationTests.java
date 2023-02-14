package org.iesvdm.tutoriales;

import org.iesvdm.tutoriales.domain.Socio;
import org.iesvdm.tutoriales.domain.Tarjeta;
import org.iesvdm.tutoriales.domain.Tutorial;
import org.iesvdm.tutoriales.domain.Comment;
import org.iesvdm.tutoriales.repository.SocioRepository;
import org.iesvdm.tutoriales.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TutorialesApplicationTests {
    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    SocioRepository socioRepository;

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



}
