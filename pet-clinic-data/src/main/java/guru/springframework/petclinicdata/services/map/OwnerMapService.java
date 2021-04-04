package guru.springframework.petclinicdata.services.map;

import guru.springframework.petclinicdata.model.Owner;
import guru.springframework.petclinicdata.model.Pet;
import guru.springframework.petclinicdata.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService {
    private final PetTypeMapService petTypeMapService;
    private final PetMapService petMapService;

    public OwnerMapService(PetTypeMapService petTypeMapService, PetMapService petMapService) {
        this.petTypeMapService = petTypeMapService;
        this.petMapService = petMapService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null) {
            object.getPets().forEach(pet -> {
                if(pet.getPetType() != null){
                    if(pet.getPetType().getId() == null)
                        pet.setPetType(petTypeMapService.save(pet.getPetType()));
                } else{
                    throw new RuntimeException("PetType is required");
                }
                if(pet.getId() == null){
                    Pet savedPet = petMapService.save(pet);
                    pet.setId(savedPet.getId());
                }
            });

            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
