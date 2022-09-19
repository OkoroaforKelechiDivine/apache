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

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }
}
