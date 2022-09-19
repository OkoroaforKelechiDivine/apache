package practice.backend.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import practice.backend.dto.request.RegisterAdminDto;
import practice.backend.dto.request.UpdateAdminDto;
import practice.backend.exception.BlogException;
import practice.backend.model.admin.Admin;
import practice.backend.model.roleType.UserType;
import practice.backend.repository.admin.AdminRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    public Admin findById(int id){
        return adminRepository.findById(id);
    }

    public Boolean adminDoesNotExistById(int id){
        return !adminRepository.existsById(id);
    }


    private String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public Admin createAdmin(RegisterAdminDto registerAdminDto) throws BlogException {
        if (Objects.equals(registerAdminDto.getEmail(), "")){
            throw new BlogException("Admin email is empty");
        }
        if (!registerAdminDto.getEmail().contains("@")){
            throw new BlogException("Invalid admin email");
        }
        if (registerAdminDto.getPassword().length() < 5){
            throw new BlogException("Admin password should not be less than 5 characters");
        }
        Admin admin = new Admin();
        admin.setCreatedDate(LocalDate.now());
        admin.setEmail(registerAdminDto.getEmail());
        admin.setGender(registerAdminDto.getGender());
        admin.setUserType(UserType.USER);
        admin.setPassword(encryptPassword(registerAdminDto.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Admin findAdminById(int id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Admin updateAdmin(UpdateAdminDto updateAdminDto) throws BlogException {
        Admin existingAdmin = findById(updateAdminDto.getId());

        if (adminDoesNotExistById(updateAdminDto.getId())){
            throw new BlogException("Admin with that id doesn't exist");
        }
        if (updateAdminDto.getEmail() != null){
            existingAdmin.setEmail(updateAdminDto.getEmail());
        }
        if (updateAdminDto.getUsername() != null){
            existingAdmin.setUsername(updateAdminDto.getUsername());
        }
        if (updateAdminDto.getPhoneNumber() != null){
            existingAdmin.setPhoneNumber(updateAdminDto.getPhoneNumber());
        }
        existingAdmin.setModifiedDate(LocalDateTime.now());
        adminRepository.save(existingAdmin);
        return existingAdmin;
    }

    @Override
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }
}
