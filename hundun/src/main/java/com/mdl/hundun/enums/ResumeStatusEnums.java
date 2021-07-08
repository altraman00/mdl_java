package com.mdl.hundun.enums;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Project : hundun-cloud
 * @Package Name : com.mdl.hundun.dtos.enums
 * @Description : TODO
 * @Author : chenlei
 * @Create Date : 2019年11月28日 19:50
 * ------------    --------------    ---------------------------------
 */
public enum ResumeStatusEnums {
    Default("60000", "全部", 0, 0),
    CONFIRMED("60011", "待确认", 1, 0),
    END("60018", "结束", 1, 0)

    ;

    private String code;
    private Integer use;
    private String name;
    private Integer stepIndex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ResumeStatusEnums(String code, String name, Integer use, int stepIndex) {
        this.code = code;
        this.name = name;
        this.use = use;
        this.stepIndex = stepIndex;
    }

    public int getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
    }


    public Integer getUse() {
        return use;
    }

    public void setUse(Integer use) {
        this.use = use;
    }

    public String getCode() {
        return code;
    }

    public ResumeStatusEnums setCode(String code) {
        this.code = code;
        return this;
    }

    public static List<ResumeStatusEnums> getUseList() {
        List<ResumeStatusEnums> resumeStatusEnums = Arrays.asList(ResumeStatusEnums.values());
        return resumeStatusEnums.stream().filter(resumeStatusEnum -> resumeStatusEnum.getUse().equals(1)).collect(Collectors.toList());
    }

    public static List<Map<String, Object>> getIdAndNameList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ResumeStatusEnums r : ResumeStatusEnums.values()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", r.getCode());
            map.put("name", r.getName());
            list.add(map);
        }
        return list;
    }

    public static ResumeStatusEnums getResumeStatusRnumsByCode(String code) {
        for (ResumeStatusEnums resumeStatusEnums : ResumeStatusEnums.values()) {
            if (code.equals(resumeStatusEnums.getCode())) {
                return resumeStatusEnums;
            }
        }
        return null;
    }
}
