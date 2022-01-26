package com.example.demo.API;

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
@RequestMapping("/api/v1/student")
public class StudentController {
    StudentService studentService;

    @Autowired      //instanzia in automatico un bean 'studentService'
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    //ritorna tutti gli studenti nella tabella
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    //inserisce uno studente nella tabella
    @PostMapping
    public void insertStudent(@RequestBody Student student){
        studentService.insertStudent(student);
    }

    //ritorna lo studente con un id che viene passato nella casella di ricerca
    @GetMapping(path = "/{id}")
    public Student getStudentById(@PathVariable("id") Long id){
        return studentService.getStudentById(id);
    }

    //modifica il nome di uno studente, sempre preso con il suo ID
    //TODO non funziona, risolvere

    @PostMapping(path = "/{id}")
    public void setStudentId(@PathVariable("id") Long oldId, @RequestParam(value = "name") String name){
        studentService.setStudentName(oldId,name);
    }


}
