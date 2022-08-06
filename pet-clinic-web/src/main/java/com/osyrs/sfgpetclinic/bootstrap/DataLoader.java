package com.osyrs.sfgpetclinic.bootstrap;

import com.osyrs.sfgpetclinic.model.Owner;
import com.osyrs.sfgpetclinic.model.Pet;
import com.osyrs.sfgpetclinic.model.PetType;
import com.osyrs.sfgpetclinic.model.Vet;
import com.osyrs.sfgpetclinic.services.OwnerService;
import com.osyrs.sfgpetclinic.services.PetTypeService;
import com.osyrs.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
        var dogType = savePetType("Dog");
        var catType = savePetType("Cat");
        System.out.println("Loaded PetTypes.......");

        var owner1 = createOwner("Malumbo","Sinkamba","Off Ring Road","Lusaka","+260972139603");
        var pet1 = assignPetToOwner(owner1,"King",dogType, LocalDate.of(2020,2,2));
        var pet2 = assignPetToOwner(owner1,"Nova",dogType, LocalDate.of(2019,12,18));
        ownerService.save(owner1);
        var owner2 = createOwner("Kaluwe","Mutemwa","24 Salama","Lusaka","+260977327251");
        var pet3 = assignPetToOwner(owner2,"Sugar",dogType, LocalDate.of(2019,12,18));
        var pet4 = assignPetToOwner(owner2,"Spike",dogType, LocalDate.of(2019,12,18));
        ownerService.save(owner2);
        System.out.println("Loaded Owners.......");

        var vet1 = saveVet("Lenganji","Sinkamba");
        var vet2 = saveVet("Suwilanji","Sinkamba");
        System.out.println("Loaded Vets.......");
    }

    private Pet assignPetToOwner(Owner owner, String name, PetType petType, LocalDate birthDate) {
        var pet = new Pet();
        pet.setOwner(owner);
        pet.setPetType(petType);
        pet.setName(name);
        pet.setBirthDate(birthDate);
        owner.getPets().add(pet);
        return pet;
    }

    private Owner createOwner(String firstName, String lastName, String address, String city, String telephone) {
        var owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        return owner;
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
        return petType;
    }
}
