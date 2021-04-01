package com.example.demo.pojo.base;

import io.swagger.annotations.ApiModelProperty;

public class Paging {

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "大小")
    private Integer pageSize;

    public Integer getPageNo() {
        if (this.pageNo == null || this.pageNo < 0) {
            pageNo = 1;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if (this.pageSize == null) {
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
