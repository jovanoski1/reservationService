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
    public TestData(CompanyRepository companyRepository, VehicleModelRepository vehicleModelRepository, VehicleTypeRepository vehicleTypeRepository, CompanyVehicleTypeRepository companyVehicleTypeRepository) {
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.companyVehicleTypeRepository = companyVehicleTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Company company = companyRepository.findById(1L).get();
//        Company company1 = companyRepository.findById(2L).get();
//
//        VehicleType vehicleType = new VehicleType("SUV","suv");
//        VehicleType vehicleType1 = new VehicleType("SEDAN", "sedan");
//        vehicleTypeRepository.save(vehicleType1);
//        vehicleTypeRepository.save(vehicleType);
//
//        Vehicle vehicleModel = new Vehicle("VW", "Tiguan", "BG-1234-WB",vehicleType,company , 30L);
//        Vehicle vehicleModel2 = new Vehicle("BMW", "530i","BG-2222-BG" ,vehicleType1, company, 45L);
//        Vehicle vehicleModel1 = new Vehicle("BMW", "530d","NS-1000-NS" ,vehicleType1, company1, 40L);
//        vehicleModelRepository.save(vehicleModel1);
//        vehicleModelRepository.save(vehicleModel);
//        vehicleModelRepository.save(vehicleModel2);

//        Company companyMiha = companyRepository.findById(1L).get();
//        Company companyProka = companyRepository.findById(2L).get();
//        VehicleType vehicleTypeSUV = vehicleTypeRepository.findById(4L).get();
//        VehicleType vehicleTypeSEDAN = vehicleTypeRepository.findById(3L).get();
//        Vehicle vehicleModelBMW = vehicleModelRepository.findById(2L).get();
//        Vehicle vehicleModelVW = vehicleModelRepository.findById(3L).get();

//        CompanyVehicleModel companyVehicleModel1 = new CompanyVehicleModel(companyMiha,vehicleModelBMW,20L);
//        CompanyVehicleModel companyVehicleModel2 = new CompanyVehicleModel(companyMiha,vehicleModelBMW,30L);
//        CompanyVehicleModel companyVehicleModel3 = new CompanyVehicleModel(companyMiha,vehicleModelVW,15L);
//        CompanyVehicleModel companyVehicleModel4 = new CompanyVehicleModel(companyProka,vehicleModelBMW,40L);
//        companyVehicleModelRepository.save(companyVehicleModel1);
//        companyVehicleModelRepository.save(companyVehicleModel2);
//        companyVehicleModelRepository.save(companyVehicleModel3);
//        companyVehicleModelRepository.save(companyVehicleModel4);

//        CompanyVehicleType companyVehicleType1 = new CompanyVehicleType(company,vehicleType);
//        CompanyVehicleType companyVehicleType2 = new CompanyVehicleType(company, vehicleType);
//        CompanyVehicleType companyVehicleType3 = new CompanyVehicleType(company,vehicleType);
//
//
//
//        companyVehicleTypeRepository.save(companyVehicleType1);
//        companyVehicleTypeRepository.save(companyVehicleType2);
//        companyVehicleTypeRepository.save(companyVehicleType3);

    }
}
