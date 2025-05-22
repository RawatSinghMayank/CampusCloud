package com.mayank.CampusCloudUniversityCampusSystem.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.mayank.CampusCloudUniversityCampusSystem.model.Admin;
import com.mayank.CampusCloudUniversityCampusSystem.model.User;
import com.mayank.CampusCloudUniversityCampusSystem.repository.AdminRepo;
import com.mayank.CampusCloudUniversityCampusSystem.repository.UserRepo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

@Component
public class    AdminDataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepo adminRepository;
    @Autowired
    UserService service;
    @Autowired
    UserRepo userRepo;

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Override
    public void run(String... args) throws Exception {

        try {
            UserRecord existingFirebaseUser = firebaseAuth.getUserByEmail("TopG16951@gmail.com");
            firebaseAuth.deleteUser(existingFirebaseUser.getUid());
            userRepo.findByEmail("TopG16951@gmail.com").ifPresent(user -> {
            userRepo.delete(user);});
        }
        catch (FirebaseAuthException e){
            System.out.println(e.getMessage());
        }
           User user = service.createUser("TopG16951@gmail.com","mayankisg","Mayank Singh Rawat","admin");
           Admin admin = new Admin();
           admin.setName(user.getName());
           admin.setUnivId("mayankisG@univ.edu");
           admin.setEmail(user.getEmail());
           admin.setDob(LocalDate.now());
           admin.setPassword(user.getPassword());
           admin.setFirebaseUid(user.getFirebaseUid());
           adminRepository.save(admin);

    }
}
