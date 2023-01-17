package com.game.store.service;

import com.github.pagehelper.PageInfo;
import com.game.store.entity.Product;

import java.util.List;

/**
 * @description 处理商品的业务层接口
 */
public interface IProductService {
    /**
     * 按照priority查找热销前五的商品数据
     * @return 返回商品数据集合
     */
    List<Product> findHotList();

    /**
     * 按照商品状态和创建时间选取新商品集合
     * @return
     */
    List<Product> findNewProductList();

    /**
     * 根据商品id查找商品
     * @param id 商品id
     * @return 返回商品信息
     */
    Product findProductById(Integer id);

    /**
     * 按照标题查询
     * @param title
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Product> findProductByTitle(String title, Integer pageNum, Integer pageSize);

//    List<Product> getProduct();

    PageInfo<Product> getProduct(Integer pageNum, Integer pageSize);


    /**
     * 新增商品
     */

    void addProduct(Product product);

    void deleteProduct(Integer id);

    void updateProduct(Integer id, String title, String price, Integer num, String imageUrl);
}
