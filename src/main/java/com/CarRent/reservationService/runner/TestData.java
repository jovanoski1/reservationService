package com.CarRent.reservationService.runner;

import com.CarRent.reservationService.model.*;
import com.CarRent.reservationService.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestData implements CommandLineRunner {
    private CompanyRepository companyRepository;
    private VehicleModelRepository vehicleModelRepository;
    private VehicleTypeRepository vehicleTypeRepository;
    private CompanyVehicleTypeRepository companyVehicleTypeRepository;
    private CompanyVehicleModelRepository companyVehicleModelRepository;

    public TestData(CompanyRepository companyRepository, VehicleModelRepository vehicleModelRepository, VehicleTypeRepository vehicleTypeRepository, CompanyVehicleTypeRepository companyVehicleTypeRepository, CompanyVehicleModelRepository companyVehicleModelRepository) {
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.companyVehicleTypeRepository = companyVehicleTypeRepository;
        this.companyVehicleModelRepository = companyVehicleModelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        VehicleType vehicleType = new VehicleType("SUV","suv");
//        VehicleType vehicleType1 = new VehicleType("SEDAN", "sedan");
//        vehicleTypeRepository.save(vehicleType1);
//        vehicleTypeRepository.save(vehicleType);
//
//        VehicleModel vehicleModel = new VehicleModel("VW", "Tiguan", vehicleType);
//        VehicleModel vehicleModel1 = new VehicleModel("BMW", "530d", vehicleType1);
//        vehicleModelRepository.save(vehicleModel1);
//        vehicleModelRepository.save(vehicleModel);

//        Company companyMiha = companyRepository.findById(1L).get();
//        Company companyProka = companyRepository.findById(2L).get();
//        VehicleType vehicleTypeSUV = vehicleTypeRepository.findById(4L).get();
//        VehicleType vehicleTypeSEDAN = vehicleTypeRepository.findById(3L).get();
//        VehicleModel vehicleModelBMW = vehicleModelRepository.findById(2L).get();
//        VehicleModel vehicleModelVW = vehicleModelRepository.findById(3L).get();
//
//        CompanyVehicleModel companyVehicleModel1 = new CompanyVehicleModel(companyMiha,vehicleModelBMW,20L);
//        CompanyVehicleModel companyVehicleModel2 = new CompanyVehicleModel(companyMiha,vehicleModelBMW,30L);
//        CompanyVehicleModel companyVehicleModel3 = new CompanyVehicleModel(companyMiha,vehicleModelVW,15L);
//        CompanyVehicleModel companyVehicleModel4 = new CompanyVehicleModel(companyProka,vehicleModelBMW,40L);
//        companyVehicleModelRepository.save(companyVehicleModel1);
//        companyVehicleModelRepository.save(companyVehicleModel2);
//        companyVehicleModelRepository.save(companyVehicleModel3);
//        companyVehicleModelRepository.save(companyVehicleModel4);
//
//        CompanyVehicleType companyVehicleType1 = new CompanyVehicleType(companyMiha,vehicleTypeSUV);
//        CompanyVehicleType companyVehicleType2 = new CompanyVehicleType(companyMiha, vehicleTypeSEDAN);
//        CompanyVehicleType companyVehicleType3 = new CompanyVehicleType(companyMiha,vehicleTypeSEDAN);
//
//        companyVehicleTypeRepository.save(companyVehicleType1);
//        companyVehicleTypeRepository.save(companyVehicleType2);
//        companyVehicleTypeRepository.save(companyVehicleType3);

    }
}
