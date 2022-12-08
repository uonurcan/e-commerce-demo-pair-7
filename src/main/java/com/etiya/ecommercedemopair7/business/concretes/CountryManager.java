package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ICountryService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.response.countries.GetCountryResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Country;
import com.etiya.ecommercedemopair7.repository.abstracts.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryManager implements ICountryService {

    private ICountryRepository countryRepository;
    private IModelMapperService mapper;

    @Autowired
    public CountryManager(ICountryRepository countryRepository, IModelMapperService mapper) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public GetCountryResponse getById(int countryId) {
        Country country = checkIfCountryExistsById(countryId);
        GetCountryResponse response = mapper.forResponse().map(country, GetCountryResponse.class);
        return response;
    }

    private Country checkIfCountryExistsById(int id) {
        Country currentCountry;
        try {
            currentCountry = this.countryRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.Country.CountryNotFound);
        }
        return currentCountry;
    }
}
