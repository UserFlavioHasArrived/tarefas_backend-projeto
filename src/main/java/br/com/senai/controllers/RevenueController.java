package br.com.senai.controllers;

import br.com.senai.models.Revenue;
import br.com.senai.repositories.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/revenue")
public class RevenueController {
    @Autowired
    RevenueRepository revenueRepository;

    //Método get, para puxar um "relatório" de todos ítens salvos no banco
    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Revenue> getAllRevenue(){
        return revenueRepository.findAll();
    }

    //Método post, para criação de um ítem no banco
    @PostMapping(value="/createRevenue",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Revenue createRevenue(@RequestBody Revenue revenue){
        //Cria um novo objeto Revenue
        Revenue newRevenue = new Revenue();
        //Seta as propriedades do Revenue
        newRevenue.setName(revenue.getName());
        newRevenue.setIntroduction(revenue.getIntroduction());
        newRevenue.setIngredient(revenue.getIngredient());
        newRevenue.setMethod_preparation(revenue.getMethod_preparation());
        newRevenue.setNutritional_information(revenue.getNutritional_information());
        //Chama o método save para salvar o objeto no banco de dados
        return revenueRepository.save(newRevenue);
    }

    //Métod update, para atualizar um ítem no banco de dados
    @PutMapping(value="/updateRevenue/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Revenue updateRevenue(@RequestBody Revenue revenue,@PathVariable Long id){
        //Pega um íten já criado e permite atualização no ítem
        Revenue getRevenue = revenueRepository
                .findById(id).orElseThrow();
        Revenue updateRevenue = new Revenue();
        //Seta os updates nos produtos já criados
        updateRevenue.setName(revenue.getName());
        updateRevenue.setIntroduction(getRevenue.getIntroduction());
        updateRevenue.setIngredient(getRevenue.getIngredient());
        updateRevenue.setMethod_preparation(getRevenue.getMethod_preparation());
        updateRevenue.setNutritional_information(getRevenue.getNutritional_information());
        //Método save para salvar as alterações no banco de dados
        return revenueRepository.save(updateRevenue);
    }

    //Chama o método deletar, para apagar um ítem do banco
    @DeleteMapping(value="/deleteRevenue/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@PathVariable pega um valor passado junto a barra de endereço
    public Revenue deleteRevenue(@PathVariable Long id){
        //Verificamos se existe o ítem no banco de dados procurando o id
        Revenue getRevenue = revenueRepository.findById(id).orElseThrow();
        //chamamos o método .delete e passamos o ítem a ser deletado
        revenueRepository.delete(getRevenue);
        return getRevenue;
    }

    @GetMapping(value = "/filtro/{palavra}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Revenue> filtrarLista(@PathVariable String palavra) {
        // Search for products using the provided 'palavra'
        List<Revenue> filteredProducts = revenueRepository.findByNameContainingIgnoreCase(palavra);

        // Check if any products were found
        if (filteredProducts.isEmpty()) {
            // No products found, return an empty list
            return Collections.emptyList();
        }

        // Products found, return the filtered list
        return filteredProducts;
    }
}
