package com.tekseries.server.core.admin.dashboardUser.user.controller;

import com.tekseries.server.core.admin.dashboardUser.user.model.request.CreateUserRequest;
import com.tekseries.server.core.admin.dashboardUser.user.model.request.UpdateUserRequest;
import com.tekseries.server.core.admin.dashboardUser.user.service.UserService;
import com.tekseries.server.entity.User;
import com.tekseries.server.infrastructure.constant.MappingConstants;
import com.tekseries.server.utils.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(MappingConstants.API_VERSION_PREFIX + MappingConstants.ADMIN + "/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseObject<?> getAllUsers() {
        return new ResponseObject<>(userService.getAllUsers());
    }

    @GetMapping("/notDelete")
    public ResponseObject<?> getUsersNotDeleted() {
        return new ResponseObject<>(userService.getUsersNotDELETED());
    }

    @GetMapping
    public ResponseObject<?> PhanTrang(@RequestParam(value = "page", defaultValue = "0") Integer page ,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return new ResponseObject<>(userService.Page(page, pageSize));
    }

    @GetMapping("/detail/{id}")
    public ResponseObject<?> detailUserById(@PathVariable("id") String id) {
        return new ResponseObject<>(userService.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseObject<?> addUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User createdUser = userService.add(createUserRequest);
        return new ResponseObject<>(createdUser, "Tạo user thành công");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject<?>> updateUser(
            @PathVariable("id") String id,
            @Valid @RequestBody UpdateUserRequest request) {
        User updatedUser = userService.update(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject<>(updatedUser, "Cập nhật user thành công"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject<?>> deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
        return ResponseEntity.ok(new ResponseObject<>(null, "Xóa user thành công"));
    }


    @PostMapping("/{id}/ban")
    public ResponseEntity<?> banUser(@PathVariable String id,
                                     @RequestParam int duration,
                                     @RequestParam String unit) {

        long durationMillis;

        switch (unit.toLowerCase()) {
            case "minutes":
                durationMillis = duration * 60 * 1000L;
                break;
            case "hours":
                durationMillis = duration * 60 * 60 * 1000L;
                break;
            case "days":
                durationMillis = duration * 24 * 60 * 60 * 1000L;
                break;
            default:
                return ResponseEntity.badRequest().body("Unit không hợp lệ. Chỉ hỗ trợ minutes, hours, days");
        }

        userService.bandUser(id, durationMillis);

        return ResponseEntity.ok("User đã bị ban trong " + duration + " " + unit);
    }

}
