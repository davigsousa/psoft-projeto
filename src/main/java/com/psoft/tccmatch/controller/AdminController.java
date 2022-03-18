package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AdmDTO;
import com.psoft.tccmatch.model.AdminUser;
import com.psoft.tccmatch.service.AdmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdmServiceImpl adminService;

    @RequestMapping(path = "/admin/novo", method = RequestMethod.POST)
    public ResponseEntity<?> cria_admin(@RequestBody AdmDTO dto) {
        try {
            AdminUser response = adminService.criar_adm(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
