package com.example.springboothw28.repstory;

import com.example.springboothw28.model.MyUser;
import com.example.springboothw28.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepstory extends JpaRepository<Order,Integer> {
    Order findByIdEquals(Integer id);
    List<Order> findAllByUserId(Integer id);
}
