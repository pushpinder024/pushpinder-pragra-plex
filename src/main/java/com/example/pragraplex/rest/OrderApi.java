package com.example.pragraplex.rest;

import com.example.pragraplex.dto.ErrorDto;
import com.example.pragraplex.entity.Order;
import com.example.pragraplex.exceptions.CustomerNotFoundException;
import com.example.pragraplex.exceptions.MovieNotFoundException;
import com.example.pragraplex.service.OrderService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class OrderApi {
    private OrderService service;

    public OrderApi(OrderService service) {
        this.service = service;
    }

    @PostMapping("/{id}/order")
    public ResponseEntity<Order> addOrder(@PathVariable("id") int customerId, @RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-CREATOR", "PUSHPINDER")
                .body(service.createOrder(customerId, order));


    }

    @GetMapping("/orders")
    public List<Order> getAll() {
        return service.findAll();
    }

    @GetMapping("/ordersById")
    public Optional<Order> findbyId(@RequestParam(name = "id") Integer id) {
        return service.findOneById(id);
    }

    @DeleteMapping("/order/remove/{orderId}")
    public void deleteOrderById(@PathVariable int orderId){
         service.deleteById(orderId);
    }

    @PostMapping("/order/addOrders/{custId}")
    public List<Order> addMultipleOrders(@PathVariable int custId,@RequestBody List<Order> orders){
        return service.createMultipleOrders(custId,orders);
    }
}
