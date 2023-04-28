package fr.simplon.titrev1_service.controller;

import fr.simplon.titrev1_service.dao.EvenementRepository;
import fr.simplon.titrev1_service.entity.Evenement;
import fr.simplon.titrev1_service.entity.Organisme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Contrôleur CRUD pour les évènements
 */

@RestController
public class EvenementController {


    private EvenementRepository repo;

    @Autowired
    public EvenementController(EvenementRepository fr) {
        this.repo = fr;

        //this.repo.save(new Evenement("Experience", "Fresque de la biodiversité","Fresque de la biodiversité, kesako? Un atelier collaboratif pour mieux comprendre le rôle des écosystèmes et imaginer ensemble comment inverser les tendances de leurs effondrement. Une expérience d’intelligence collective qui parle autant à la tête qu’au coeur.", LocalDateTime.of(2023, 8, 9, 10,00), LocalDateTime.of(2023, 8, 9, 17, 00), 10, 10, 5, "Nathalie Richard", "photo1.png" ));
    }

    @GetMapping(path = "/rest/evenements")
    public List<Evenement> getEvenement() {

        return repo.findAll();
    }


    @GetMapping("/rest/api/evenements")
    @ResponseBody
    public List<Evenement> getAllEventsAsJson() {
        List<Evenement> evenements = repo.findAll();
        return evenements;
    }


    @GetMapping(path = "/rest/evenements/{id}")
    public Evenement getEventDetails(@PathVariable Long id)throws NoSuchElementException {
        return repo.findById(id).orElseThrow();
    }


    @PostMapping("/rest/evenements")
    public Evenement addEvent(@RequestBody Evenement newEvent) {
        return repo.save(newEvent);
    }


    @PutMapping("/rest/evenements/{id}")
    public Evenement updateEvent(@RequestBody Evenement newEvent, @PathVariable Long id) {
        return repo.findById(id)
                .map(evenement -> {
                    evenement.setType(newEvent.getType());
                    evenement.setTitre(newEvent.getTitre());
                    evenement.setDescription(newEvent.getDescription());
                    evenement.setDateDebut(newEvent.getDateDebut());
                    evenement.setDateFin(newEvent.getDateFin());
                    evenement.setPlaces_totales(newEvent.getPlaces_totales());
                    evenement.setPlaces_restantes(newEvent.getPlaces_restantes());
                    evenement.setTarif(newEvent.getTarif());
                    evenement.setIntervenant(newEvent.getIntervenant());
                    evenement.setPhoto(newEvent.getPhoto());
                    return repo.save(evenement);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return repo.save(newEvent);
                });
    }


    @DeleteMapping("/rest/evenements/{id}")
    public void deleteEvent(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
