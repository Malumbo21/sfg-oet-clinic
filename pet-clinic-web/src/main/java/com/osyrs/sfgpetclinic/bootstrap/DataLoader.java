package com.osyrs.sfgpetclinic.bootstrap;

import com.osyrs.sfgpetclinic.model.Owner;
import com.osyrs.sfgpetclinic.model.PetType;
import com.osyrs.sfgpetclinic.model.Vet;
import com.osyrs.sfgpetclinic.services.OwnerService;
import com.osyrs.sfgpetclinic.services.PetTypeService;
import com.osyrs.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        var owner1 = saveOwner("Malumbo","Sinkamba");
        var owner2 = saveOwner("Kaluwe","Mutemwa");
        System.out.println("Loaded Owners.......");
        var vet1 = saveVet("Lenganji","Sinkamba");
        var vet2 = saveVet("Suwilanji","Sinkamba");
        System.out.println("Loaded Vets.......");
        var dogType = savePetType("Dog");
        var catType = savePetType("Cat");
        System.out.println("Loaded PetTypes.......");
    }

    private Owner saveOwner(String firstName, String lastName) {
        var owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        return ownerService.save(owner);
    }
    private Vet saveVet(String firstName, String lastName) {
        var owner = new Vet();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        return vetService.save(owner);
    }
    private PetType savePetType(String name){
        var petType= new PetType();
        petType.setName(name);
        return petTypeService.save(petType);
    }
}
