package com.mdl.hundun.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Project : hundun-cloud
 * @Package Name : com.mdl.hundun.dtos.enums
 * @Description : TODO
 * @Author : chenlei
 * @Create Date : 2019年11月28日 19:16
 * ------------    --------------    ---------------------------------
 */
public enum DeliverFlowEnum {

    START(1001, "开始"),
    END(1005, "结束")
    ;

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String name;

    DeliverFlowEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DeliverFlowEnum valueOf(Integer value) {
        for (DeliverFlowEnum s : DeliverFlowEnum.values()) {
            if (s.code.equals(value)) {
                return s;
            }
        }
        throw new RuntimeException("Undefined DeliverFlowEnum " + value);
    }
}
