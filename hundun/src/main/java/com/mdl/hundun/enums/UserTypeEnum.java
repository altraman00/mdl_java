package com.mdl.hundun.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Project : hundun-cloud
 * @Package Name : com.mdl.hundun.dtos.enums
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2019年12月13日 15:49
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public enum UserTypeEnum {

    USER_TYPE_INNER("inner", "内部员工"),
    USER_TYPE_OUTER("outer", "外部员工"),
    USER_TYPE_HR("hr", "HR")
    ;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String name;

    UserTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }


}
