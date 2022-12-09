package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IStreetService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.business.response.streets.GetStreetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "streets")
public class StreetsController {

    private IStreetService streetService;

    @Autowired
    public StreetsController(IStreetService streetService) {
        this.streetService = streetService;
    }


    @GetMapping
    public List<GetAllStreetResponse> getAll() {
        return streetService.getAll();
    }

    @GetMapping("/{id}")
    public GetStreetResponse getById(@PathVariable int id) {
        return this.streetService.getById(id);
    }
}

