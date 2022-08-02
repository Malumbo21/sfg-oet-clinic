package com.osyrs.sfgpetclinic.bootstrap;

import com.osyrs.sfgpetclinic.model.Owner;
import com.osyrs.sfgpetclinic.model.Vet;
import com.osyrs.sfgpetclinic.services.OwnerService;
import com.osyrs.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        var owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Malumbo");
        owner1.setLastName("Sinkamba");

        ownerService.save(owner1);

        var owner2 = new Owner();
        var id = 2L;
        owner2.setId(id);
        owner2.setFirstName("Kaluwe");
        owner2.setLastName("Mutemwa");
        /* DataSource dataSource = DataSourceBuilder.create()
                .url("")
                .username("")
                .password("")
                .driverClassName("")
                .build();*/
        ownerService.save(owner2);

        System.out.println("Loaded Owners.......");

        var vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Lenganji");
        vet1.setLastName("Sinkamba");

        vetService.save(vet1);

        var vet2 = new Vet();
        vet2.setId(1L);
        vet2.setFirstName("Suwilanji");
        vet2.setLastName("Sinkamba");

        vetService.save(vet2);

        System.out.println("Loaded Vets.......");
    }
}
