package com.example.demo.service;

import com.example.demo.pojo.base.PageResponse;
import com.example.demo.pojo.request.UserCreateRequest;
import com.example.demo.pojo.request.UserInfoRequest;
import com.example.demo.pojo.request.UserUpdateRequest;
import com.example.demo.pojo.response.UserInfoResponse;

public interface UserService {

    PageResponse<UserInfoResponse> list(UserInfoRequest request);

    void create(UserCreateRequest request);

    void update(UserUpdateRequest request);

    void delete(Integer id);
}
