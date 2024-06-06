package br.com.senai.controllers;

import br.com.senai.models.ChecklistActivities;
import br.com.senai.repositories.CheckActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin (origins = "http://localhost:5173")
@RestController
@RequestMapping("/checklistActivities")
public class CheckActivitiesController {
    @Autowired
    CheckActivitiesRepository checkRepository;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChecklistActivities> getAllChecklistActivities(){
        return checkRepository.findAll();
    }

    @PostMapping(value="/createChecklistActivities",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChecklistActivities createChecklistActivities(@RequestBody ChecklistActivities checklistActivities){
        //Cria um novo objeto Product
        ChecklistActivities newChecklistActivities = new ChecklistActivities();
        //Seta as propriedades do Product
        newChecklistActivities.setName_activities(checklistActivities.getName_activities());
        newChecklistActivities.setType(checklistActivities.getType());
        newChecklistActivities.setDate_hour(checklistActivities.getDate_hour());
        //Chama o método save para salvar o objeto no banco de dados
        return checkRepository.save(newChecklistActivities);
    }

    @PutMapping(value="/updateChecklistActivities",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChecklistActivities updateChecklistActivities(@RequestBody ChecklistActivities checklistActivities){
        ChecklistActivities getChecklistActivities = checkRepository
                .findById(checklistActivities.getId()).orElseThrow();
        ChecklistActivities updateChecklistActivities = new ChecklistActivities();

        updateChecklistActivities.setId(checklistActivities.getId());
        updateChecklistActivities.setName_activities(checklistActivities.getName_activities());
        updateChecklistActivities.setType(checklistActivities.getType());
        updateChecklistActivities.setDate_hour(checklistActivities.getDate_hour());

        return checkRepository.save(updateChecklistActivities);
    }
    //Método deletar Product
    @DeleteMapping(value="/deleteChecklistActivities/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@PathVariable pega um valor passado junto a barra de endereço
    public ChecklistActivities deleteChecklistActivities(@PathVariable Long id){
        //Verificamos se existe o café no banco de dados procurando o id
        ChecklistActivities getChecklistActivities = checkRepository.findById(id).orElseThrow();
        //chamamos o método .delete e passamos o café a ser deletado
        checkRepository.delete(getChecklistActivities);
        return getChecklistActivities;
    }
}
