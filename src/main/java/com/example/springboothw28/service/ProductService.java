package com.example.springboothw28.service;

import com.example.springboothw28.handling.ApiException;
import com.example.springboothw28.model.Product;
import com.example.springboothw28.repstory.ProducrRepstory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProducrRepstory producrRepstory;
    public List<Product> getAll(){
        return producrRepstory.findAll();
    }

    public void add(Product product){
        producrRepstory.save(product);
    }
    public Boolean update(Integer id,Product product){
       Product p=producrRepstory.findByIdEquals(id);
       if(p==null){
           return  false;
       }
       product.setId(p.getId());
       product.setOrders(p.getOrders());
       producrRepstory.save(product);
       return true;
    }

    public Boolean delete(Integer id){
        Product p=producrRepstory.findByIdEquals(id);
        if(p==null){
            return  false;
        }
       producrRepstory.delete(p);
        return true;
    }

    public Product getProduectId(Integer id){
        Product product=producrRepstory.findByIdEquals(id);
        if(product==null){
            throw new ApiException("The id is not fount ");
        }
        return product;
    }
}
