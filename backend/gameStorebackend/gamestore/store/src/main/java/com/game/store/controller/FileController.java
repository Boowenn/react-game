package com.game.store.controller;

import com.game.store.controller.ex.*;
import com.game.store.service.IUserService;
import com.game.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @description
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController{

    @Autowired
    private IUserService userService;

    /*设置上传文件的最大值*/
    public static final int AVATAR_MAX_SIZE=10 * 1024 * 1024;
    /*限制上传文件的类型*/
    public static final List<String> AVATAR_TYPES = new ArrayList<>();
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    /**
     * MultipartFile接口时SpringMVC提供的一个接口，这个接口为我们包装了
     * 获取文件数据(任何类型的文件File都可以),Springboot整合了SpringMVC
     * 只需要在处理请求的方法参数列表上声明一个参数为MultipartFile即可
     * @param session
     * @param file
     * @return
     */
    @PostMapping
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file){
        //判断文件是否为null
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        //判断文件大小
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：" + AVATAR_TYPES);
        }
        //获取当前文件的绝对路径
        //String parent = session.getServletContext().getRealPath("upload");
        String parent = "/Users/zhaoxinlei/workspace/StoreRebuild/store/src/main/resources/static/avatar";
        System.out.println(parent);
        //保存头像文件的文件夹
        File dir = new File(parent);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //保存头像文件的文件名
        String suffix ="";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if(beginIndex>0){
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir,filename);
        //执行保存文件操作
        try{
            file.transferTo(dest);
        }catch (IllegalStateException e){
            //抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或者删除");
        }catch (IOException e){
            //抛出异常
            throw new FileUploadIOException("上传文件时读写错误,请稍后重新尝试");
        }
        //从Session中获取Uid和username
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        //将头像写入数据库
        userService.changeAvatar(uid,filename,username);
        //返回成功头像路径
        String avatar = "../avatar/"+filename;
        System.out.println(filename);
        System.out.println(avatar);
        return new JsonResult<>(OK,avatar);
    }

    //用户更改密码
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public JsonResult<Void> changePassword(@RequestParam("file") MultipartFile file,
                                           HttpSession session){


        System.out.println(file);
        //判断文件是否为null
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }

        //判断文件大小
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：" + AVATAR_TYPES);
        }

        System.out.println(System.getProperty("user.dir"));


        String parent = System.getProperty("user.dir") + "/image";
        System.out.println(parent);

        //保存头像文件的文件夹
        File dir = new File(parent);
        System.out.println(dir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //保存头像文件的文件名
        String suffix ="";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if(beginIndex>0){
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir,filename);
        //执行保存文件操作
        try{
            file.transferTo(dest);
        }catch (IllegalStateException e){
            //抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或者删除");
        }catch (IOException e){
            //抛出异常
            throw new FileUploadIOException("上传文件时读写错误,请稍后重新尝试");
        }
        //返回成功头像路径
        String productImg = "/newImg/"+filename;
        System.out.println(filename);
        return new JsonResult<>(OK,productImg);

//        return new JsonResult<>(OK,"文件上传");

    }
}
