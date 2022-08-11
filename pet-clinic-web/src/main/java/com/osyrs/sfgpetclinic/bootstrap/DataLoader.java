package com.osyrs.sfgpetclinic.bootstrap;

import com.osyrs.sfgpetclinic.model.*;
import com.osyrs.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Component
@RestController
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count==0) {
            loadData();
        }
    }

    private void loadData() {
        var dogType = savePetType("Dog");
        var catType = savePetType("Cat");
        System.out.println("Loaded PetTypes.......");

        var owner1 = createOwner("Malumbo","Sinkamba","Off Ring Road","Lusaka","+260972139603");
        var pet1 = assignPetToOwner(owner1,"King",dogType, LocalDate.of(2020,2,2));
        var pet2 = assignPetToOwner(owner1,"Nova",catType, LocalDate.of(2019,12,18));
        ownerService.save(owner1);
        var owner2 = createOwner("Kaluwe","Mutemwa","24 Salama","Lusaka","+260977327251");
        var pet3 = assignPetToOwner(owner2,"Sugar",dogType, LocalDate.of(2019,12,18));
        var pet4 = assignPetToOwner(owner2,"Spike",catType, LocalDate.of(2019,12,18));
        ownerService.save(owner2);
        System.out.println("Loaded Owners.......");
        var visitDescription = "Sneezing cat";
        var visitDate = LocalDate.now();
        var visit = createVisit(pet1, visitDescription, visitDate);
        visitService.save(visit);
        Specialty radiology = getSpecialty("Radiology");
        var surgery = getSpecialty("Surgery");
        var dentistry = getSpecialty("Dentistry");
        System.out.println("Loaded Specialties.....");

        var vet1 = saveVet("Lenganji","Sinkamba",surgery);
        var vet2 = saveVet("Suwilanji","Sinkamba",radiology);
        System.out.println("Loaded Vets.......");
    }

    private Visit createVisit(Pet pet1, String visitDescription, LocalDate visitDate) {
        var visit = new Visit();
        visit.setDate(visitDate);
        visit.setPet(pet1);
        visit.setDescription(visitDescription);
        return visit;
    }

    private Specialty getSpecialty(String description) {
        var specialty = new Specialty();
        specialty.setDescription(description);
        return specialtyService.save(specialty);
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
    private Vet saveVet(String firstName, String lastName,Specialty... specialties) {
        var vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.getSpecialities().addAll(List.of(specialties));
        return vetService.save(vet);
    }
    private PetType savePetType(String name){
        var petType= new PetType();
        petType.setName(name);
        return petTypeService.save(petType);
    }
}
