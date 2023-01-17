package com.game.store.service.Impl;

import com.game.store.entity.Product;
import com.game.store.service.IProductService;
import com.game.store.service.ex.UpdateException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.game.store.mappers.ProductMapper;
import com.game.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 处理商品的业务层实现类
 */
@Service
public class IProductServiceImpl implements IProductService {
    @Autowired(required = false)
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        //查找数据
        List<Product> res = productMapper.findHotList();
        return res;
    }

    @Override
    public List<Product> findNewProductList() {
        //查找数据
        List<Product> res = productMapper.findNewProductList();
        return res;
    }

    @Override
    public Product findProductById(Integer id) {
        //根据id查询商品信息
        Product res = productMapper.findProductById(id);
        //判断商品存在或者商品状态是否为上架
        if(res==null){
            throw new ProductNotFoundException("查询商品不存在");
        }
//        if(res.getStatus()!=1){
//            throw new ProductStatusException("查询商品尚未上架");
//        }
        //传输商品信息
        return res;
    }

    @Override
    public PageInfo<Product> findProductByTitle(String title, Integer pageNum, Integer pageSize) {
        //开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //查询结果
        List<Product> res = productMapper.findProductByTitle(title);
        //返回结果
        return new PageInfo<>(res);
    }

//    @Override
//    public List<Product> getProduct() {
//        //查找数据
//        List<Product> res = productMapper.getProduct();
//        return res;
//    }

    @Override
    public PageInfo<Product> getProduct(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //查找数据
        List<Product> res = productMapper.getProduct();
        return new PageInfo<>(res);
    }

    @Override
    public void addProduct(Product product) {

        Integer row = productMapper.addProduct(product);
        System.out.println("查看");
        System.out.println(row);
        if(row!=1){
            throw new UpdateException("新增商品异常");
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        Integer row = productMapper.deleteProduct(id);
        if(row!=1){
            throw new UpdateException("删除用户失败");
        }
    }

    @Override
    public void updateProduct(Integer id, String title, String price, Integer num, String imageUrl) {
//        Product res = productMapper.findProductById(id);
//        if(res==null){
//            throw new UserNotFoundException("商品数据不存在");
//        }
//        user.setUid(uid);
//        user.setUsername(username);
//        user.setModifiedUser(username);
//        user.setModifiedTime(new Date());

        Integer row = productMapper.updateProduct(id, title, price, num, imageUrl);
        if(row!=1){
            throw new UpdateException("更新用户数据时产生未知异常");
        }
    }
}
