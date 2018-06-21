package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.Good;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.mapper.ShopCarMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘亮 on 2017/12/17.
 */
@Service
public class GoodService extends BaseService<GoodMapper,Good> {

    private final GoodMapper goodMapper ;
    private final ShopCarMapper shopCarMapper;

    public GoodService(GoodMapper goodMapper, ShopCarMapper shopCarMapper) {
        this.goodMapper = goodMapper;
        this.shopCarMapper = shopCarMapper;
    }

    public Page<Good> loadGoods(String name,Page page){
        page.getRecords();
        List<Good> list = goodMapper.selectPage(
                page,
                new EntityWrapper<Good>()
                        .like(StringUtils.isNotEmpty(name),"good_name",name)
                        .orderBy("good_price")
        );
        if (CollectionUtils.isNotEmpty(list)){
            page.setRecords(list);
        }
        shopCarMapper.selectShopCarById("5576fa8c08494576990de729406ebb31");
        return page ;
    }


    public List<Good> BatchInsertOrUpdate(List<Good> goods){
        List<Good> goodList = new ArrayList<>();
        goods.forEach(
            good -> {
                if(good.getGood_id()==null){
//                    good.setGood_id();
                    goodMapper.insert(good);
                }else {
                    goodMapper.updateById(good);
                }
                goodList.add(goodMapper.selectList(new EntityWrapper<Good>().eq("id",good.getGood_id())).get(0));
            }
        );
        return goodList;
    }


}
