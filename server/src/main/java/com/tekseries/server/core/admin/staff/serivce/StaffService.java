package com.tekseries.server.core.admin.staff.serivce;

import com.tekseries.server.core.admin.staff.model.request.StaffRequest;
import com.tekseries.server.core.admin.staff.model.response.StaffResponse;
import com.tekseries.server.entity.Staff;

import java.util.List;

public interface StaffService {
    List<StaffResponse> getAll();
    StaffResponse detailByCode(String code);
    StaffResponse add(StaffRequest sp);
}
