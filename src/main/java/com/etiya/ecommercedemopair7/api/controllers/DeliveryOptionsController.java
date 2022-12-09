package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IDeliveryOptionService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.deliveryOptions.AddDeliveryOptionRequest;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.AddDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetAllDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetDeliveryOptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "delivery-options")
public class DeliveryOptionsController {

    private IDeliveryOptionService deliveryOptionService;

    @Autowired
    public DeliveryOptionsController(IDeliveryOptionService deliveryOptionService) {
        this.deliveryOptionService = deliveryOptionService;
    }

    @GetMapping
    public List<GetAllDeliveryOptionResponse> getAll() {
        return deliveryOptionService.getAll();
    }

    @GetMapping("/{id}")
    public GetDeliveryOptionResponse getById(@PathVariable int id) {
        return deliveryOptionService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AddDeliveryOptionResponse> add(@RequestBody AddDeliveryOptionRequest addDeliveryOptionRequest) {
        return new ResponseEntity<AddDeliveryOptionResponse>(deliveryOptionService.add(addDeliveryOptionRequest), HttpStatus.CREATED);
    }
}
