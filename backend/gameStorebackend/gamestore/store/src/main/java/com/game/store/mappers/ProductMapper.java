package com.game.store.mappers;

import com.game.store.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 处理商品数据的Mapper接口
 */
public interface ProductMapper {
    /**
     * 按照priority查找热销前五的商品数据
     * @return 返回商品数据集合
     */
    List<Product> findHotList();

    /**
     * 按照创建时间查找新到的商品
     * @return 返回新商品列表
     */
    List<Product> findNewProductList();

    /**
     * 按照商品的id查找商品
     * @param id
     * @return
     */
    Product findProductById(Integer id);

    /**
     * 按照输入的标题查找
     * @param title
     * @return
     */
    List<Product> findProductByTitle(String title);

    List<Product> getProduct();

    /**
     * 新增product
     * @return
     */
    Integer addProduct(Product product);

    Integer deleteProduct(Integer id);

    Integer updateProduct(@Param("id") Integer id,
                          @Param("title") String title,
                          @Param("price") String price,
                          @Param("num") Integer num,
                          @Param("imageUrl") String imageUrl);
}
