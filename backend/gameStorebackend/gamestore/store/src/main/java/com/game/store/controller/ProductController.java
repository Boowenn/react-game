package com.game.store.controller;

import com.game.store.entity.Product;
import com.game.store.service.IProductService;
import com.game.store.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * @description  处理商品相关请求的控制类
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired(required = false)
    private IProductService productService;

    /**
     *  返回热销商品
     * @return
     */
    @RequestMapping(value = "/hot_list",method = RequestMethod.GET)
    public JsonResult<List<Product>> getHotList(){
        //查询热销商品
        List<Product> data = productService.findHotList();
        return new JsonResult<>(OK,data);
    }

    /**
     *  返回新商品
     * @return
     */
    @RequestMapping(value = "/new_list",method = RequestMethod.GET)
    public JsonResult<List<Product>> getNewList(){
        //查询新到商品
        List<Product> data = productService.findNewProductList();
        return new JsonResult<>(OK,data);
    }

    @RequestMapping(value = "/{id}")
    public  JsonResult<Product> findProductById(@PathVariable("id")Integer id){
        //按照获得的id查询商品
        Product data = productService.findProductById(id);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping(value = "/{pageNum}/{pageSize}/{title}",method = RequestMethod.GET)
    public JsonResult<PageInfo<Product>> findWithTitle(@PathVariable("pageNum") Integer pageNum,
                                                       @PathVariable("pageSize") Integer pageSize,
                                                       @PathVariable("title") String title){
        PageInfo<Product> res = productService.findProductByTitle(title, pageNum, pageSize);
        return new JsonResult<>(OK,res);
    }

//    @RequestMapping(value = "/all_list",method = RequestMethod.GET)
//    public JsonResult<List<Product>> getProduct(){
//        //查询热销商品
//        List<Product> data = productService.getProduct();
//        return new JsonResult<>(OK,data);
//    }

    @RequestMapping(value = "/all_list/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public JsonResult<PageInfo<Product>> getProduct(@PathVariable("pageNum") Integer pageNum,
                                                    @PathVariable("pageSize") Integer pageSize,
                                                    HttpSession session){

        if (session.getAttribute("token") == null) {
            return new JsonResult<>(OK, "admin已失效");
        } else {
            PageInfo<Product> data = productService.getProduct(pageNum, pageSize);
            return new JsonResult<>(OK,data);
        }
    }

    // 新增商品
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult<Void> addProduct(Product product,
                                       HttpSession session) {

        if (session.getAttribute("token") == null) {
            return new JsonResult<>(OK, "admin已失效");
        } else {
            //执行插入操作
            productService.addProduct(product);
            return new JsonResult<>(OK);
        }
    }

    // 删除商品
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public JsonResult<Void> deleteProduct(@RequestParam("id") Integer id,HttpSession session){
        System.out.println(id);

        if (session.getAttribute("token") == null) {
            return new JsonResult<>(OK, "admin已失效");
        } else {
            //Service内部已经重新写入
            productService.deleteProduct(id);
            return new JsonResult<>(OK,"该商品已成功删除");
        }
    }

    // 修改商品
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<Void> updateProduct(@RequestParam("id") Integer id,
                                          @RequestParam("title") String title,
                                          @RequestParam("price") String price,
                                          @RequestParam("num") Integer num,
                                          @RequestParam("imageUrl") String imageUrl,
                                          HttpSession session){
        System.out.println(id);

        if (session.getAttribute("token") == null) {
            return new JsonResult<>(OK, "admin已失效");
        } else {
            //Service内部已经重新写入
            productService.updateProduct(id, title, price, num, imageUrl);
            return new JsonResult<>(OK,"该商品已成功删除");
        }
    }

}
