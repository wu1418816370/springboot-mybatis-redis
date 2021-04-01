package com.example.demo.pojo.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class UserUpdateRequest {

    @ApiModelProperty(value = "学生id")
    @NotNull(message = "学生id不能为空")
    private Integer id;

    @ApiModelProperty(value = "学生名称")
    private String name;

    @ApiModelProperty(value = "学生年龄")
    private Integer age;

    @ApiModelProperty(value = "学生分数")
    private String score;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
