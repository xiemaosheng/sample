package com.nd.repository;

import com.nd.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口名称：
 * 接口描述：
 * 创建人：newtonk
 * 创建日期：2016/8/17 0017
 */
public interface BookRepository extends JpaRepository<Book,Integer>{
}
