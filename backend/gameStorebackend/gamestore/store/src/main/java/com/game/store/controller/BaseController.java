package com.game.store.controller;

import com.game.store.controller.ex.*;
import com.game.store.service.ex.*;
import com.game.store.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @description 全局处理异常的基类
 */
public class BaseController {
    /*操作成功状态码*/
    public static final int OK = 200;
    /**
     * 1.当出现了value内的异常之一，就会将下方的方法作为新的控制器方法进行执行
     *   因此该方法的返回值也同时是返回给前端的页面
     * 2.此外还自动将异常对象传递到此方法的参数列表中，这里使用Throwable e来接收
     **/
    @ExceptionHandler(ServiceException.class) //统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        //用户
        if (e instanceof UsernameDuplicateException){
            result.setState(4000); //表示用户名重复
            result.setMessage(e.getMessage());
        } else if (e instanceof UserNotFoundException){
            result.setState(4001); //表示用户数据不存在
            result.setMessage(e.getMessage());
        }else if (e instanceof PasswordNotMatchException){
            result.setState(4002); //表示用户密码不匹配
            result.setMessage(e.getMessage());
        }
        //Address
        else if (e instanceof AddressCountLimitException){
            result.setState(4003); //表示用户登陆地址超出限额
            result.setMessage(e.getMessage());
        } else if (e instanceof AddressNotFoundException){
            result.setState(4005); //用户收货地址未找到
            result.setMessage(e.getMessage());
        }
        //Product
        else if (e instanceof ProductNotFoundException){
            result.setState(4006); //商品未找到异常
            result.setMessage(e.getMessage());
        }else if (e instanceof ProductStatusException){
            result.setState(4007); //商品状态异常
            result.setMessage(e.getMessage());
        }else if (e instanceof CartInfoNotExistsException){
            result.setState(4008); //购物车商品不存在异常
            result.setMessage(e.getMessage());
        }
        //file
        else if (e instanceof FileEmptyException){
            result.setState(5000); //上传文件为空异常
            result.setMessage(e.getMessage());
        }else if (e instanceof FileSizeException){
            result.setState(5002); //上传文件大小超出限制异常
            result.setMessage(e.getMessage());
        }else if (e instanceof FileTypeException){
            result.setState(5003); //上传文件类型异常
            result.setMessage(e.getMessage());
        }else if (e instanceof FileStateException){
            result.setState(5004); //上传文件类型异常
            result.setMessage(e.getMessage());
        }else if (e instanceof FileUploadIOException){
            result.setState(5005); //上传文件读写异常
            result.setMessage(e.getMessage());
        }
        //favorite
//        else if(e instanceof FavoriteExistException){
//            result.setState(6000);
//            result.setMessage(e.getMessage());
//        }

        //其他数据库或者验证码异常
        else if (e instanceof InsertException){
            result.setState(9000); //插入时异常
            result.setMessage(e.getMessage());
        }else if (e instanceof UpdateException){
            result.setState(9001); //更新时异常
            result.setMessage(e.getMessage());
        }else if (e instanceof DeleteException){
            result.setState(9002); //删除时异常
            result.setMessage(e.getMessage());
        }else if (e instanceof ValidCodeNotMatchException){
            result.setState(9002); //表示验证码不匹配
            result.setMessage(e.getMessage());
        }
        //返回异常处理结果
        return result;
    }

    /**
     * Description : 从session中获取用户uid
     * @param session springboot启动时生成的session对象
     **/
    public final Integer getUserIdFromSession(HttpSession session){
        String uidStr = session.getAttribute("uid").toString();
        return Integer.valueOf(uidStr);
    }

    //从session中获取用户username
    public final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
