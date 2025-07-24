package com.tekseries.server.core.admin.staff.controller;

import com.tekseries.server.core.admin.staff.model.request.StaffRequest;
import com.tekseries.server.core.admin.staff.model.response.ResponseObject;
import com.tekseries.server.core.admin.staff.model.response.StaffResponse;
import com.tekseries.server.core.admin.staff.serivce.StaffService;
import com.tekseries.server.infrastructure.constant.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MappingConstants.API_STAFF_PREFIX)
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping
    public ResponseObject<?> getAll(){
         return   new ResponseObject<>(staffService.getAll());
    }

    @GetMapping("detail/{code}")
    public ResponseObject<?> detail(@PathVariable("code") String code){
        return   new ResponseObject<>(staffService.detailByCode(code));
    }

    @PostMapping("add")
    public ResponseObject<?> add(@RequestBody StaffRequest sp){
        return   new ResponseObject<>(staffService.add(sp),"Them thanh cong");
    }

}
