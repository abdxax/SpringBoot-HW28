package com.example.springboothw28.service;

import com.example.springboothw28.DTO.OrderStatus;
import com.example.springboothw28.DTO.StatusTdo;
import com.example.springboothw28.handling.ApiException;
import com.example.springboothw28.model.MyUser;
import com.example.springboothw28.model.Order;
import com.example.springboothw28.model.Product;
import com.example.springboothw28.repstory.OrderRepstory;
import com.example.springboothw28.repstory.ProducrRepstory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepstory orderRepstory;
    private final ProducrRepstory producrRepstory;
    public List<Order> orders(MyUser user){
        return orderRepstory.findAll();
    }

    public void add(MyUser user, OrderStatus order){
        Product product=producrRepstory.findByIdEquals(order.getPoductId());
        if(product==null){
            throw new ApiException("The id prodect not fount ");
        }
        Integer totalPrice=order.getQuantity()*product.getPrice();
        String status="new";
        Order o=new Order(null,order.getQuantity(),totalPrice, new Date().toString(),status,user,product);
        orderRepstory.save(o);
    }

    public Boolean update(Integer id,MyUser user,Order order){

        Order order1=orderRepstory.findByIdEquals(id);
        if(order1==null||order1.getUser().getId()!=user.getId()){
            return false;
        }
        order.setUser(user);
        order.setId(order1.getId());
        order.setProduct(order1.getProduct());
        orderRepstory.save(order);
        return true;
    }
    public Boolean delete(Integer id,MyUser user){

        Order order1=orderRepstory.findByIdEquals(id);
        if(order1==null||order1.getUser().getId()!=user.getId()){
            return false;
        }
        if(order1.getStatus().equals("inProgress")||order1.getStatus().equals("completed")){
            throw new ApiException("Can not delete ");
        }

        orderRepstory.delete(order1);
        return true;
    }

    public Boolean changeStatus(Integer id, StatusTdo statusTdo){
        Order order=orderRepstory.findByIdEquals(id);
        if(order==null){
            return false;
        }
        order.setStatus(statusTdo.getStatus());
        orderRepstory.save(order);
        return true;
    }

    public Order getOrder(Integer id){
        Order order=orderRepstory.findByIdEquals(id);
        if(order==null){
            throw new ApiException("The id is not fount");
        }
        return order;
    }

    public List<Order> userOrders(MyUser user){
        List<Order> orders=orderRepstory.findAllByUserId(user.getId());
        if(orders.isEmpty()){
            throw new ApiException("No order ");
        }
        return orders;
    }
}
