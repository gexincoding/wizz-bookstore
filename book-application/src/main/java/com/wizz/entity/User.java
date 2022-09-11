package com.wizz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //书籍id
    private Long bookId;

    //书籍名字
    private String bookName;

    //书籍分类
    private Integer bookCategoryId;

    //借书者id
    private Integer bookBorrowerId;

    //书籍状态
    private Integer bookStatus;

    //书籍编码
    private String bookISBN;

}
