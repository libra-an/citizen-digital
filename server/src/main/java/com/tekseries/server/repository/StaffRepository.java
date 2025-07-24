package com.tekseries.server.repository;

import com.tekseries.server.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StaffRepository extends JpaRepository<Staff, String> {
}
