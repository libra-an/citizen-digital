package com.tekseries.server.core.admin.dashboardUser.user.service;

import com.tekseries.server.core.admin.dashboardUser.user.model.request.CreateUserRequest;
import com.tekseries.server.core.admin.dashboardUser.user.model.request.UpdateUserRequest;
import com.tekseries.server.core.admin.dashboardUser.user.model.response.UserResponse;
import com.tekseries.server.entity.User;
import com.tekseries.server.utils.PageableObject;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    PageableObject<UserResponse> Page(Integer page, Integer pageSize);

    UserResponse getUserById(String id);

    User add (CreateUserRequest createUserRequest);

    User update(String id, UpdateUserRequest request);

    void delete(String id);

    void deleteDB(String id);

    List<UserResponse> getUsersNotDELETED();

    void bandUser(String id, long durationMillis);
}
