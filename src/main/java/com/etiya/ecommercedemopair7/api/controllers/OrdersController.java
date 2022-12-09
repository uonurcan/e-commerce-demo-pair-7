package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IOrderService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.orders.AddOrderRequest;
import com.etiya.ecommercedemopair7.business.response.orders.AddOrderResponse;
import com.etiya.ecommercedemopair7.business.response.orders.GetAllOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "orders")
public class OrdersController {

    private IOrderService orderService;

    @Autowired
    public OrdersController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<GetAllOrderResponse> getAll() {
        return orderService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<AddOrderResponse> add(@RequestBody AddOrderRequest addOrderRequest) {
        return new ResponseEntity<AddOrderResponse>(orderService.add(addOrderRequest), HttpStatus.CREATED);
    }
}
