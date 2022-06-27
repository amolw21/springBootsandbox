package com.codesimplify.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codesimplify.restservices.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
