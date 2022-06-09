package com.cab.repository;

import com.cab.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {


    @Query("select s from User s where s.username=:username AND  s.password=:password")
    Customer validateCustomer(@PathParam("username") String username, @PathParam("password") String password);

}
