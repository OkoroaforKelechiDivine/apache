package practice.backend.service.admin;


import practice.backend.model.admin.Admin;
import practice.backend.model.post.Post;

import java.util.List;

public interface AdminService {

    List<Admin> findAllAdmins();

    Admin findAdminByPost(Post post);
}
