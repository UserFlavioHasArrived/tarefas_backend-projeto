package br.com.senai.controllers;

import br.com.senai.models.Users;
import br.com.senai.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/createUsers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users createUsers(@RequestBody Users users) {
        //Cria um novo objeto Userss
        Users newUsers = new Users();
        //Seta as propriedades do Coffee
        newUsers.setName(users.getName());
        newUsers.setPassword(users.getPassword());
        newUsers.setEmail(users.getEmail());
        //Chama o método save para salvar o objeto no banco de dados
        return userRepository.save(newUsers);
    }

    @PutMapping(value = "/updateUsers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users updateUsers(@RequestBody Users user) {
        Users getUsers = userRepository
                .findById(user.getId()).orElseThrow();
        Users updateUsers = new Users();

        updateUsers.setId(user.getId());
        updateUsers.setName(user.getName());
        updateUsers.setPassword(user.getPassword());
        updateUsers.setEmail(user.getEmail());


        return userRepository.save(updateUsers);
    }

    //Método deletar coffee
    @DeleteMapping(value = "/deleteUsers/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@PathVariable pega um valor passado junto a barra de endereço
    public Users deleteUsers(@PathVariable Long id) {
        //Verificamos se existe o café no banco de dados procurando o id
        Users getUsers = userRepository.findById(id).orElseThrow();
        //chamamos o método .delete e passamos o café a ser deletado
        userRepository.delete(getUsers);
        return getUsers;
    }

    @PostMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<Users> usersOptional = userRepository.findByName(loginRequest.getName());

        if (usersOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Users user = usersOptional.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(user); // You can decide what information to return in the response
    }
}
