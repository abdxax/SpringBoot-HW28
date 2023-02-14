package com.example.springboothw28.controller;

import com.example.springboothw28.DTO.StatusTdo;
import com.example.springboothw28.model.MyUser;
import com.example.springboothw28.model.Order;
import com.example.springboothw28.model.Product;
import com.example.springboothw28.service.MyUserServiceCURD;
import com.example.springboothw28.service.OrderService;
import com.example.springboothw28.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/prodect")
@RequiredArgsConstructor
public class ProdectController {
    private final ProductService productService;
    private final OrderService orderService;
    private final MyUserServiceCURD userServiceCURD;
    @GetMapping("/getAll")
    public List<Product> productList(){
        return productService.getAll();
    }
    @PostMapping("/add")
    public ResponseEntity addprodcut(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Product product){
        productService.add(product);
        return ResponseEntity.status(200).body("Added done");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid Product product,@AuthenticationPrincipal MyUser user){
        Boolean res=productService.update(id,product);
        if(!res){
            return ResponseEntity.status(400).body("The id is error");
        }
        return ResponseEntity.status(200).body("Done update");

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        Boolean res=productService.delete(id);
        if(!res){
            return ResponseEntity.status(400).body("The id is error");
        }
        return ResponseEntity.status(200).body("Done delete");

    }
@PutMapping("/updateStatus/{orderId}")
    public ResponseEntity changeStatus(@AuthenticationPrincipal MyUser user,@PathVariable Integer orderId,@RequestBody @Valid StatusTdo statusTdo){
        Boolean res=orderService.changeStatus(orderId,statusTdo);
        if(!res){
            return ResponseEntity.status(200).body("The id order is not found ");
        }
        return ResponseEntity.status(200).body("Done update sTatus");

    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity getUser(@AuthenticationPrincipal MyUser user,@PathVariable Integer id){
        MyUser user1=userServiceCURD.getUser(id);
       return ResponseEntity.status(200).body(user1);
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<List<Order>> getOrderByUser(@AuthenticationPrincipal MyUser user,@PathVariable Integer id){
        MyUser user1=userServiceCURD.getUser(id);
        List<Order> orders=orderService.userOrders(user1);
        return ResponseEntity.status(200).body(orders);
    }
}
