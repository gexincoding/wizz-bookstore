package com.wizz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long bookId;

    private String bookName;

    private Integer bookCategoryId;

    private String bookCategoryName;

    private String bookStatusName;

    private Integer bookBorrowerId;

    private Integer bookStatusId;

    private String bookISBN;

    private Integer bookLeftNumbers;

    private String bookImage;

}
