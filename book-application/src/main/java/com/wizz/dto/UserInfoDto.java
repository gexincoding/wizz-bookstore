package com.wizz.dto;

import com.wizz.dao.User;
import lombok.Data;

/**
 * @author xialinrui
 */
@Data
public class UserInfoDto extends User {
    private String deptName;
}
