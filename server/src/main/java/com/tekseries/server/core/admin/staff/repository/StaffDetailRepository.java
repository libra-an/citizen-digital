package com.tekseries.server.core.admin.staff.repository;

import com.tekseries.server.entity.Staff;
import com.tekseries.server.repository.StaffRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDetailRepository extends StaffRepository {
    @Query("SELECT nv from Staff nv where nv.code = ?1")
    Staff detailByCode(String code);
}
