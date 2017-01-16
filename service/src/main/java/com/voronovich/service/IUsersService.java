package com.voronovich.service;

import com.voronovich.entity.UsersEntity;


public interface IUsersService {

    UsersEntity get(String login, String password);

    UsersEntity get(String login);

    UsersEntity getByEmail(String email);
}
