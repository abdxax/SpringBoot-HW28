package com.example.springboothw28.controller;

import com.example.springboothw28.DTO.OrderStatus;
import com.example.springboothw28.model.MyUser;
import com.example.springboothw28.model.Order;
import com.example.springboothw28.model.Product;
import com.example.springboothw28.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/getAll")
    public List<Order> orderList(@AuthenticationPrincipal MyUser user){
        return orderService.orders(user);
    }
    @PostMapping("/add")
    public ResponseEntity addprodcut(@AuthenticationPrincipal MyUser user, @RequestBody @Valid OrderStatus order){
       orderService.add(user,order);
        return ResponseEntity.status(200).body("Added done");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid Order order,@AuthenticationPrincipal MyUser user){
        Boolean res=orderService.update(id,user,order);
        if(!res){
            return ResponseEntity.status(400).body("The id is error");
        }
        return ResponseEntity.status(200).body("Done update");

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        Boolean res=orderService.delete(id,user);
        if(!res){
            return ResponseEntity.status(400).body("The id is error");
        }
        return ResponseEntity.status(200).body("Done delete");

    }
@GetMapping("/getmyorder")
    public ResponseEntity<List<Order>> getMyOrder(@AuthenticationPrincipal MyUser user){
        List<Order> orders=orderService.userOrders(user);
        return ResponseEntity.status(200).body(orders);
    }
}
