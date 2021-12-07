package com.example.demo.DataAccess;

import com.example.demo.Model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/* Interfaccia in JPA per interfacciarsi con un database qualsiasi.
Crea in automatico i metodi in base a quali categorie sono presenti nella tabella.
Strumento utilissimo per manipolari database di qualunque tipo (basta cambiare l'URL nei settings e sei apposto)
senza dover scrivere nessun comando in SQL.
//TODO RICORDARE
 */
public interface Repository extends CrudRepository<Student, Long> {
    List<Student> findStudentByName(String name);

    List<Student> findAll();

    Student getStudentById(Long id);

}
