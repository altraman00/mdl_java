package com.mdl.hundun.dto.ppt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePptDTO {

    /**
     * ppt_urls : ["http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(1).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(2).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(3).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(4).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(5).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(6).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(7).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(8).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(9).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(10).JPG","http://yxs-app.oss-cn-beijing.aliyuncs.com/1578671845_aaa(11).JPG"]
     * video_id : e4c2b5fab5daee7cffca3029d2d42d87
     * video_name : 一、产品观：用户是价值核心
     */

    private String video_id;
    private String video_name;
    private List<String> ppt_urls;

}
