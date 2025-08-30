package com.tekseries.server.core.admin.dashboardUser.user.service.impl;

import com.tekseries.server.core.admin.dashboardUser.user.model.request.CreateUserRequest;
import com.tekseries.server.core.admin.dashboardUser.user.model.request.UpdateUserRequest;
import com.tekseries.server.core.admin.dashboardUser.user.model.response.UserResponse;
import com.tekseries.server.core.admin.dashboardUser.user.repository.AdminUserRepository;
import com.tekseries.server.core.admin.dashboardUser.user.service.UserService;
import com.tekseries.server.entity.User;
import com.tekseries.server.infrastructure.constant.EntityStatus;
import com.tekseries.server.utils.MapperUtils;
import com.tekseries.server.utils.PageableObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    public long minutes(int m) { return m * 60 * 1000L; }
    public long hours(int h) { return h * 60 * 60 * 1000L; }


    @Override
    public List<UserResponse> getAllUsers() {
        return adminUserRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();
    }
    @Override
    public List<UserResponse> getUsersNotDELETED() {
        return adminUserRepository.findUsersNotDeleted(EntityStatus.DELETED)
                .stream()
                .map(UserResponse::new)
                .toList();
    }


    @Override
    public PageableObject<UserResponse> Page(Integer page, Integer pageSize) {
        Pageable result = PageRequest.of(page, pageSize);
        Page<User> pageUser = adminUserRepository.findAll(result);
        Page<UserResponse> pageResponse = pageUser.map(user -> MapperUtils.map(user, UserResponse.class));
        return new PageableObject<>(pageResponse);
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = adminUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        return new UserResponse(user);
    }

//    @Override
//    public User add(CreateUserRequest createUserRequest) {
//        if (adminUserRepository.existsByEmail(createUserRequest.getEmail())) {
//            throw new IllegalArgumentException("Email đã được sử dụng");
//        }
//
//        List<String> validRoles = List.of("USER", "ADMIN");
//        if (!validRoles.contains(createUserRequest.getRole().toUpperCase())) {
//            throw new IllegalArgumentException("Role không hợp lệ: " + createUserRequest.getRole());
//        }
//        User user = MapperUtils.map(createUserRequest, User.class);
//
//        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
//
//        user.setStatus(EntityStatus.ACTIVE);
//
//        return adminUserRepository.save(user);
//    }

    @Override
    public User add(CreateUserRequest createUserRequest) {
        if (adminUserRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }

        List<String> validRoles = List.of("USER", "ADMIN");
        String role = createUserRequest.getRole().toUpperCase();
        if (!validRoles.contains(role)) {
            throw new IllegalArgumentException("Role không hợp lệ: " + createUserRequest.getRole());
        }

        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setFullName(createUserRequest.getFullName());
        user.setMajor(createUserRequest.getMajor());
        user.setClassField(createUserRequest.getClassName());
        user.setRole(role);
        user.setProfilePicture(createUserRequest.getProfilePicture());
        user.setBio(createUserRequest.getBio());
        user.setStatus(EntityStatus.ACTIVE);

        return adminUserRepository.save(user);
    }



    @Override
    public User update(String id, UpdateUserRequest request) {
        User user = adminUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại với id: " + id));

        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            user.setFullName(request.getFullName());
        }

        if (request.getMajor() != null) {
            user.setMajor(request.getMajor());
        }

        if (request.getClassName() != null) {
            user.setClassField(request.getClassName());
        }

        if (request.getRole() != null) {
            List<String> validRoles = List.of("USER", "ADMIN");
            String role = request.getRole().toUpperCase();
            if (!validRoles.contains(role)) {
                throw new IllegalArgumentException("Role không hợp lệ: " + request.getRole());
            }
            user.setRole(role);
        }

        if (request.getProfilePicture() != null) {
            user.setProfilePicture(request.getProfilePicture());
        }

        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }

        if (request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }

        return adminUserRepository.save(user);
    }


    @Override
    public void delete(String id) {
        User user = adminUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại với id: " + id));

        if (user.getStatus() == EntityStatus.DELETED) {
            throw new IllegalStateException("User đã bị xóa trước đó");
        }

        user.setStatus(EntityStatus.DELETED);
        adminUserRepository.save(user);
    }

    @Override
    public void deleteDB(String id) {
        if (!adminUserRepository.existsById(id)) {
            throw new IllegalArgumentException("User không tồn tại với id: " + id);
        }
        adminUserRepository.deleteById(id);
    }


    public void bandUser(String userId, long durationMillis) {
        User user = adminUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại với id: " + userId));

        if (user.getStatus() == EntityStatus.BANNED) {
            throw new IllegalStateException("User đã bị ban trước đó");
        }

        user.setStatus(EntityStatus.BANNED);
        user.setBanExpiryDate(System.currentTimeMillis() + durationMillis);
        adminUserRepository.save(user);
    }


    @Scheduled(fixedRate = 60000)
    public void unbanUsers() {
        long now = System.currentTimeMillis();
        List<User> bannedUsers = adminUserRepository.findByStatus(EntityStatus.BANNED);

        for (User user : bannedUsers) {
            if (user.getBanExpiryDate() != null && user.getBanExpiryDate() <= now) {
                user.setStatus(EntityStatus.ACTIVE);
                user.setBanExpiryDate(null);
                adminUserRepository.save(user);
            }
        }
    }


}
