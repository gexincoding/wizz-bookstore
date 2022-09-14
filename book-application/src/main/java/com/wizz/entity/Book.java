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
    private Long bookId;   //图书id

    private String bookName;   //图书名

    private String bookISBN;   //ISBN编号

    private Integer bookStatusId;  //状态

    private Integer bookLeftNumbers;   //余量

    private Integer bookTotalNumbers;   //总量

    private Integer bookLentNumbers;    //借出量

    private String bookImage;    //例图

}
