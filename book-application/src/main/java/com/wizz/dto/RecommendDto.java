package com.wizz.dto;

import com.wizz.dao.Recommend;
import lombok.Data;

/**
 * @author xialinrui
 */
@Data
public class RecommendDto extends Recommend {
    private String username;
    private String category;
}
