package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ICityService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.response.cities.GetAllCityResponse;
import com.etiya.ecommercedemopair7.business.response.cities.GetCityResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.City;
import com.etiya.ecommercedemopair7.repository.abstracts.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements ICityService {

    private ICityRepository cityRepository;
    private IModelMapperService mapper;

    @Autowired
    public CityManager(ICityRepository cityRepository, IModelMapperService mapper) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllCityResponse> getAll() {
        List<City> cities = cityRepository.findAll();
        List<GetAllCityResponse> response = cities.stream()
                .map(city -> mapper.forResponse().map(city, GetAllCityResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetCityResponse getById(int cityId) {
        City city = checkIfCityExistsById(cityId);
        GetCityResponse response = mapper.forResponse().map(city, GetCityResponse.class);
        return response;
    }

    private City checkIfCityExistsById(int id) {
        City currentCity;
        try {
            currentCity = this.cityRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.City.CityNotFound);
        }
        return currentCity;
    }
}

