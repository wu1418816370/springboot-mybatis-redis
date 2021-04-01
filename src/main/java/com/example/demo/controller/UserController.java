package com.example.demo.controller;

import com.example.demo.pojo.base.BaseResponse;
import com.example.demo.pojo.base.PageResponse;
import com.example.demo.pojo.request.UserCreateRequest;
import com.example.demo.pojo.request.UserInfoRequest;
import com.example.demo.pojo.request.UserUpdateRequest;
import com.example.demo.pojo.response.UserInfoResponse;
import com.example.demo.service.UserService;
import com.example.demo.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("用户相关api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "可根据用户id分页查询")
    @PostMapping(value = "/list")
    public BaseResponse<PageResponse<UserInfoResponse>> list(@RequestBody UserInfoRequest request) {
        return ResultUtils.ok(userService.list(request));
    }

    @ApiOperation(value = "创建用户")
    @PostMapping(value = "/create")
    public BaseResponse<Void> create(@RequestBody @Valid UserCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.failed(110, bindingResult.getFieldError().getDefaultMessage());
        }
        userService.create(request);
        return ResultUtils.ok();
    }

    @ApiOperation(value = "更新用户")
    @PostMapping(value = "/update")
    public BaseResponse<Void> update(@RequestBody @Valid UserUpdateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.failed(110, bindingResult.getFieldError().getDefaultMessage());
        }
        userService.update(request);
        return ResultUtils.ok();
    }

    @ApiOperation(value = "删除用户")
    @GetMapping(value = "/delete")
    public BaseResponse<Void> delete(@ApiParam("用户id") @RequestParam(name = "id", required = false) Integer id) {
        if (id == null || id < 0) {
            return ResultUtils.failed(110, "id不能为空/id不能小于0");
        }
        userService.delete(id);
        return ResultUtils.ok();
    }
}
