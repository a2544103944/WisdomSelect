package com.cjj.common.mybatis.page;
import lombok.Data;

/**
 * @author 陈建军
 * @version 1.0
 */
@Data
public class PageQuery {
    //当前页码
    private Long current;
    //每页显示条数
    private Long size;
}
