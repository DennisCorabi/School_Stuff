package com.example.demo.DataAccess;

import com.example.demo.Model.Classi;
import org.springframework.data.repository.CrudRepository;

public interface ClassDBaccess extends CrudRepository<Classi, Long> {
    Classi getClassiByAnnoAndSezioneAndArticolazione(String anno, String sezione, String articolazione);

}
