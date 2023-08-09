package com.cjj.common.mybatis.page;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 陈建军
 * @version 1.0
 */
@Data

public class PageResult<T> {
    private int code;
    private String msg;
    private PageData<T> data;

    public PageResult(int code, String msg, PageData<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageData<T> {
        private Long total;
        private List<T> records;
    }

    public static <T> PageResult<T> success(IPage page) {
        return new PageResult<>(HttpStatus.HTTP_OK,"请求成功",new PageData<>(page.getTotal(),page.getRecords()));
    }
}
