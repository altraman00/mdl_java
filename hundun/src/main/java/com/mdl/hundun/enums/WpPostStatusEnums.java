package com.mdl.hundun.enums;

import java.util.*;
import java.util.stream.Collectors;

public enum WpPostStatusEnums {

    //简历投递
    RESUME_DELIVER_Y(100011, "1001x","简历投递","等等HR吧", "待面试"),
    RESUME_DELIVER_N(100012, "1001x","简历投递","有缘再见", "结束"),

    //到面
    INTERVIE_ARRIVE_Y(100021, "1002x","到访","面试中", "面试中"),
    INTERVIE_ARRIVE_N(100022, "1002x","到访","有缘再见", "结束"),

    //面试
    INTERVIE_TAKE_Y(100031, "1003x","面试","期待您来", "待到岗"),
    INTERVIE_TAKE_N(100032, "1003x","面试","有缘再见", "结束"),

    //到岗
    TRAIN_ENTRY_Y(100041, "1004x","报到","期待您入职", "待入职"),
    TRAIN_ENTRY_N(100042, "1004x","报到","有缘再见", "结束"),

    //入职
    REAL_ENTRY_Y(100051, "1005x","入职","欢迎加入", "成功入职"),
    REAL_ENTRY_N(100052, "1005x","入职","有缘再见", "结束")

    ;


    private Integer code;
    private String stageCode;
    private String stageName;
    private String deliverStatus;
    private String sharerStatus;

    public Integer getCode() {
        return code;
    }

    public WpPostStatusEnums setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public String getSharerStatus() {
        return sharerStatus;
    }

    public void setSharerStatus(String sharerStatus) {
        this.sharerStatus = sharerStatus;
    }


    WpPostStatusEnums(Integer code,String stageCode, String stageName, String deliverStatus, String sharerStatus) {
        this.code = code;
        this.stageCode = stageCode;
        this.stageName = stageName;
        this.deliverStatus = deliverStatus;
        this.sharerStatus = sharerStatus;
    }

    public static List<WpPostStatusEnums> getUseList() {
        List<WpPostStatusEnums> resumeStatusEnums = Arrays.asList(WpPostStatusEnums.values());
        return resumeStatusEnums.stream().filter(resumeStatusEnum -> resumeStatusEnum.getDeliverStatus().equals(1)).collect(Collectors.toList());
    }

    public static List<Map<String, Object>> getIdAndNameList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (WpPostStatusEnums r : WpPostStatusEnums.values()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", r.getCode());
            map.put("stageCode", r.getStageCode());
            map.put("stageName", r.getStageName());
            map.put("deliverStatus", r.getDeliverStatus());
            map.put("sharerStatus", r.getSharerStatus());
            list.add(map);
        }
        return list;
    }


    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    public static WpPostStatusEnums getResumeStatusEnumsByCode(Integer code) {
        for (WpPostStatusEnums resumeStatusEnums : WpPostStatusEnums.values()) {
            if (code.equals(resumeStatusEnums.getCode())) {
                return resumeStatusEnums;
            }
        }
        return null;
    }


    /**
     * 根据stageCode获取stageName
     * @param stageCode
     * @return
     */
    public static String getStageNameByStageCode(String stageCode) {
        for (WpPostStatusEnums resumeStatusEnums : WpPostStatusEnums.values()) {
            if (stageCode.equals(resumeStatusEnums.getStageCode())) {
                return resumeStatusEnums.getStageName();
            }
        }
        return stageCode;
    }

}
