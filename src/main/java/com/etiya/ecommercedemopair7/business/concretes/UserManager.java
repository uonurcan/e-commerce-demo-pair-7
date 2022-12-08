package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IUserService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.response.users.GetUserResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.User;
import com.etiya.ecommercedemopair7.repository.abstracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements IUserService {
    private IUserRepository userRepository;
    private IModelMapperService mapper;

    @Autowired
    UserManager(IUserRepository userRepository, IModelMapperService mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public GetUserResponse getById(int userId) {
        User user = checkIfUserExistsById(userId);
        GetUserResponse response = mapper.forResponse().map(user, GetUserResponse.class);
        return response;
    }

    @Override
    public User getByUserId(int userId) {
        return checkIfUserExistsById(userId);
    }

    private User checkIfUserExistsById(int userId) {
        User currentUser;
        try {
            currentUser = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.User.UserNotFound);
        }
        return currentUser;
    }
}
