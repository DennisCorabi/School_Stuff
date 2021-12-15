package com.example.demo.API;

import com.example.demo.Model.Classi;
import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
Controller layer.
Si occupa di interfacciarsi direttamente con l'utente, dal momento che gestise le richieste HTTP
che vengono inviate dall'utente, che richiede 'qualcosa' o 'di far qualcosa' al database.
In una web-application, verrà poi rimpiazzato dalla parte front-end dell'app.
 */

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    StudentService studentService;

    @Autowired      //instanzia in automatico un bean 'studentService'
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //LATO STUDENTI

    //inserisce uno studente nella tabella
    @PostMapping(path = "/student")
    public void insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
    }

    //ritorna lo studente con un id che viene passato nella casella di ricerca
    @GetMapping(path = "/student/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    //LATO CLASSI

    //Insersci nuova classe nell' elenco delle classi
    @PostMapping(path="/classe")
    public void setClasse(@RequestBody Classi classe){
        studentService.setClasse(classe);
    }
    //Ritorna una classe le cui specifiche sono presenti nella richiesta inviata al server.
    @GetMapping(path = "/classe")
    public Classi getClasse(@RequestBody Classi classe) {
        return studentService.getClasse(classe);
    }

}
