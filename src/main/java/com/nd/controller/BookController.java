package com.nd.controller;

import com.nd.common.exception.NiceException;
import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.repository.BookRepository;
import com.nd.uc.UcApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.nd.common.exception.NiceErrorCodes.PASSWORD_CRYPTO_ERROR;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/8/17 0017
 */
@RestController
@RequestMapping(value = "/books")
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private UcApiService ucApiService;


    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/all")
    public Object getAll(@AuthenticationPrincipal UserInfo userInfo){
       Object o =bookRepository.findAll();
        System.out.println(userInfo.getNickName());
        return o;
    }

    @GetMapping(value = "/token")
    public Object getToken(){
        return ucApiService.getBearToken();
    }

    @GetMapping(value = "error")
    public Object throwException(String param){
        if(param.equals("true")) {
            logger.info("error");
            throw new NiceException(PASSWORD_CRYPTO_ERROR);
        }
        return "hello";
    }

    @PostMapping(value = "valid")
    public Object valid(@Valid @RequestBody User user){
        return user;
    }
}
