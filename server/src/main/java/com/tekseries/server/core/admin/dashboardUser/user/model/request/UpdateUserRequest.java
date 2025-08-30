package com.tekseries.server.core.admin.dashboardUser.user.model.request;

import com.tekseries.server.infrastructure.constant.EntityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    private String major;

    private String className;

    @NotBlank(message = "Vai trò không được để trống")
    private String role;

    private String profilePicture;

    @Size(max = 500, message = "Bio không được vượt quá 500 ký tự")
    private String bio;

    private EntityStatus status;
}
