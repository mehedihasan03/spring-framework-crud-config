/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.model.Student;
import com.example.model.UserModel;
import com.example.model.req.LoginPayload;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Instructor
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class LoginController {

    @Autowired
    SessionFactory sessionFactory;

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginPayload payload) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            //HQL = Hibernate Query Language.
            Session s = sessionFactory.openSession();
            Query q = s.createQuery("FROM UserModel where username=:username");
            q.setParameter("username", payload.getUsername());
            List<UserModel> entityList = q.list();

            if (entityList != null && entityList.size() > 0) {
                UserModel user = entityList.get(0);
                if (user.getPassword().equals(payload.getPassword())) {
                    map.put("message", "Login Successful");
                    map.put("status", "Success");
                    map.put("data", user);
                    return ResponseEntity.ok(map);
                }
            }
            map.put("message", "Login fail!");
            map.put("status", "Failed");
            map.put("data", null);
            return ResponseEntity.status(412).body(map);
        } catch (Exception e) {
            map.put("message", e.getLocalizedMessage());
            map.put("status", "Failed");
            map.put("data", null);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping(value = "/user/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> save(@RequestBody UserModel entity) {
        try {
            Session session = sessionFactory.openSession();
            session.save(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Save failed");
        }
    }
}
