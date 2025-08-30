package com.tekseries.server.core.admin.dashboardUser.user.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password phải có ít nhất 6 ký tự")
    private String password;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    private String major;

    private String className;

    @NotBlank(message = "Role không được để trống")
    private String role;

    private String profilePicture;

    @Size(max = 500, message = "Bio không được vượt quá 500 ký tự")
    private String bio;
}
