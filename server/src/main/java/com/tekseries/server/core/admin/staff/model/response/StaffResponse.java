package com.tekseries.server.core.admin.staff.model.response;


import com.tekseries.server.entity.Staff;
import lombok.Data;

@Data
public class StaffResponse {
    private String id;

    private String code;

    private String name;

    private String email;

    public StaffResponse(Staff s) {
        this.id = s.getId();
        this.code = s.getCode();
        this.name = s.getName();
        this.email = s.getEmail();
    }
}
