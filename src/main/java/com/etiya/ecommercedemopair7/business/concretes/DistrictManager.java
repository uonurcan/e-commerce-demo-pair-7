package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IDistrictService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.response.districts.GetAllDistrictResponse;
import com.etiya.ecommercedemopair7.business.response.districts.GetDistrictResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.District;
import com.etiya.ecommercedemopair7.repository.abstracts.IDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictManager implements IDistrictService {

    private IDistrictRepository districtRepository;
    private IModelMapperService mapper;

    @Autowired
    public DistrictManager(IDistrictRepository districtRepository, IModelMapperService mapper) {
        this.districtRepository = districtRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllDistrictResponse> getAll() {
        List<District> districts = districtRepository.findAll();
        List<GetAllDistrictResponse> response = districts.stream()
                .map(district -> mapper.forResponse().map(district, GetAllDistrictResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetDistrictResponse getById(int districtId) {
        District district = checkIfDistrictExistsById(districtId);
        GetDistrictResponse response = mapper.forResponse().map(district, GetDistrictResponse.class);
        return response;
    }

    private District checkIfDistrictExistsById(int id) {
        District currentDistrict;
        try {
            currentDistrict = this.districtRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.District.districtNotFound);
        }
        return currentDistrict;
    }
}
