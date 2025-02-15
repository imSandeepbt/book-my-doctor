package com.te.bookmydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.bookmydoctor.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);
}
