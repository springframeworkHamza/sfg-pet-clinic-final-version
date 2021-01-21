package guru.springframework.sfgpetclinic.model.repositories;

import guru.springframework.sfgpetclinic.model.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
