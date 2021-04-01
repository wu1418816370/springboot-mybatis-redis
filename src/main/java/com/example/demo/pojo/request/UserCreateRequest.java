package com.example.demo.pojo.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserCreateRequest {

    @ApiModelProperty(value = "学生名称")
    @NotBlank(message = "学生名称不能为空")
    private String name;

    @ApiModelProperty(value = "学生年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotBlank(message = "分数不能为空")
    @ApiModelProperty(value = "学生分数")
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
