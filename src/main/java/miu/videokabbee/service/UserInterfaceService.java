package miu.videokabbee.service;


import miu.videokabbee.domain.Users;

public interface UserInterfaceService {
    String register(Users users);
    Users findById(Long id);
}
