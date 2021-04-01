package com.example.demo.pojo.request;

import com.example.demo.pojo.base.Paging;
import io.swagger.annotations.ApiModelProperty;

public class UserInfoRequest extends Paging {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
