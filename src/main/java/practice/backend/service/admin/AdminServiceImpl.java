package practice.backend.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.backend.model.admin.Admin;
import practice.backend.model.post.Post;
import practice.backend.repository.admin.AdminRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findAdminByPost(Post post) {
        return null;
    }
}
