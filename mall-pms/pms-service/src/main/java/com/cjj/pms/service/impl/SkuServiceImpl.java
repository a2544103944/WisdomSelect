package com.cjj.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.pms.pojo.entity.Sku;
import com.cjj.pms.service.SkuService;
import com.cjj.pms.mapper.SkuMapper;
import org.springframework.stereotype.Service;

/**
* @author 25441
* @description 针对表【pms_sku(商品库存表)】的数据库操作Service实现
* @createDate 2023-08-10 15:59:09
*/
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku>
    implements SkuService{

}




