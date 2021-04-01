package com.example.demo.service.impl;

import com.example.demo.exception.BussinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import com.example.demo.pojo.base.PageResponse;
import com.example.demo.pojo.request.UserCreateRequest;
import com.example.demo.pojo.request.UserInfoRequest;
import com.example.demo.pojo.request.UserUpdateRequest;
import com.example.demo.pojo.response.UserInfoResponse;
import com.example.demo.service.UserService;
import com.example.demo.util.JacksonUtil;
import com.example.demo.util.RedisUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageResponse<UserInfoResponse> list(UserInfoRequest request) {
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (request.getId() != null && request.getId() > 0) {
            Object userInfo = redisUtils.get(request.getId().toString());
            if (userInfo != null && StringUtils.isNotBlank(userInfo.toString())) {
                return JacksonUtil.toEntity(userInfo.toString(), new TypeReference<PageResponse<UserInfoResponse>>() {
                });
            }
            criteria.andIdEqualTo(request.getId());
        }

        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(users);

        List<UserInfoResponse> userInfos = new ArrayList<>();
        for (User user : users) {
            UserInfoResponse userInfo = new UserInfoResponse();
            BeanUtils.copyProperties(user, userInfo);
            userInfos.add(userInfo);
        }
        PageResponse<UserInfoResponse> response = new PageResponse<>();
        response.setList(userInfos);
        response.setPageNo(pageInfo.getPageNum());
        response.setPageSize(pageInfo.getPageSize());
        response.setTotal(pageInfo.getTotal());
        response.setPages(pageInfo.getPages());
        if (users.size() == 1 && request.getId() != null) {
            redisUtils.set(request.getId().toString(), JacksonUtil.toJson(JacksonUtil.toJson(response)), 60 * 10);
        }
        return response;
    }

    @Override
    public void create(UserCreateRequest request) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(request.getName()).andAgeEqualTo(request.getAge())
                .andScoreEqualTo(request.getScore());
        List<User> users = userMapper.selectByExample(userExample);
        if (!CollectionUtils.isEmpty(users)) {
            throw BussinessException.create(110, "请勿重复创建！");
        }
        User user = new User();
        user.setAge(request.getAge());
        user.setName(request.getName());
        user.setScore(request.getScore());
        userMapper.insertSelective(user);
    }

    @Override
    public void update(UserUpdateRequest request) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(request.getId());
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            throw BussinessException.create(110, "当前用户不存在！");
        }
        User user = users.get(0);
        BeanUtils.copyProperties(request, user);
        // 双删，保持缓存一致性
        String userId = user.getId().toString();
        if (redisUtils.hasKey(userId)){
            redisUtils.del(userId);
        }
        userMapper.updateByExampleSelective(user, userExample);

        redisUtils.del(userId);
    }

    @Override
    public void delete(Integer id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            throw BussinessException.create(110, "用户不存在！");
        }
        userMapper.deleteByExample(userExample);
    }
}
