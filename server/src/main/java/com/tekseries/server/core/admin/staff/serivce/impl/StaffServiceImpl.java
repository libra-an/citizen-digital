package com.tekseries.server.core.admin.staff.serivce.impl;

import com.tekseries.server.core.admin.staff.model.request.StaffRequest;
import com.tekseries.server.core.admin.staff.model.response.StaffResponse;
import com.tekseries.server.core.admin.staff.repository.StaffDetailRepository;
import com.tekseries.server.core.admin.staff.serivce.StaffService;
import com.tekseries.server.entity.Staff;
import com.tekseries.server.repository.StaffRepository;
import com.tekseries.server.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffDetailRepository staffDetailRepository;

    @Override
    public List<StaffResponse> getAll() {
        return staffDetailRepository.findAll()
                .stream()
                .map(StaffResponse::new)
                .toList();
    }

    @Override
    public StaffResponse detailByCode(String code) {
        Staff s =staffDetailRepository.detailByCode(code);
        return new StaffResponse(s);
    }

    @Override
    public StaffResponse add(StaffRequest sp) {
        Staff s = MapperUtils.map(sp, Staff.class);
        staffDetailRepository.save(s);
        return new StaffResponse(s);
    }
}
