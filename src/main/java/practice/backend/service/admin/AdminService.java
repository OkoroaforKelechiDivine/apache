package practice.backend.service.admin;

import practice.backend.dto.request.RegisterAdminDto;
import practice.backend.dto.request.UpdateAdminDto;
import practice.backend.exception.BlogException;
import practice.backend.model.admin.Admin;
import practice.backend.model.user.BlogUser;

import java.util.List;

public interface AdminService {

    Admin createAdmin(RegisterAdminDto registerAdminDto) throws BlogException;

    Admin findAdminById(int id);

    Admin findAdminByEmail(String email);

    Admin updateAdmin(UpdateAdminDto updateAdminDto) throws BlogException;

    void deleteAdmin(int id);

    List<Admin> findAllAdmins();
}
