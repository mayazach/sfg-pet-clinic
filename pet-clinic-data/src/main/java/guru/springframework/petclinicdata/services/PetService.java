package guru.springframework.petclinicdata.services;

import guru.springframework.petclinicdata.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet owner);

    Set<Pet> findAll();
}
