package com.example.springboothw28.repstory;

import com.example.springboothw28.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducrRepstory extends JpaRepository<Product,Integer> {
    Product findByIdEquals(Integer id);
}
