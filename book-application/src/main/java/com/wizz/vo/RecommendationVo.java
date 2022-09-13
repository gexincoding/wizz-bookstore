package com.wizz.vo;

import lombok.Data;

@Data
public class RecommendationVo {
    private String bookName;
    private String publisher;
    private String categoryName;
    private String reasons;
    private String username;
}
