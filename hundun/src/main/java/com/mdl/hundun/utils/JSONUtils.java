package com.mdl.hundun.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mdl.hundun.dto.course.CourseBaseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 解析json字符串
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class JSONUtils {

	private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);
	/**
	 * 将一个对象json化
	 *
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		return objectToJson(obj,false);
	}
	/**
	 * 对象格式化，支持格式化输出
	 * @param obj
	 * @param pretty
	 * @return
	 */
	public static String objectToJson(Object obj,boolean pretty) {
		ObjectMapper om = new ObjectMapper();
		String result = null;
		try {
			SimpleFilterProvider fProvider = new SimpleFilterProvider();
			fProvider.setFailOnUnknownId(false);
			om.setFilters(fProvider);
			if(pretty) {
				result = om.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			}else{
				result = om.writeValueAsString(obj);
			}
		} catch (JsonProcessingException e) {
			logger.error("Json处理异常",e);
		}
		return result;
	}

	/**
	 * 将json 转换为指定类型对象集合
	 * @param json
	 * @param clazz
	 * @return
	 */
	public  static <T> List<T> jsonToList(String json, Class<T> clazz){
		ObjectMapper om = new ObjectMapper();

		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JavaType javaType = om.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
//		JavaType javaType = getCollectionType(mapper, ArrayList.class, getGenericsType());
		List<T> result = null;
		try {
			result = om.readValue(json,javaType);
		} catch (IOException e) {
			logger.error("jsonToList 处理异常",e);
		}
		return result;
	}

	/**
	 * 串行化指定对象的指定字段
	 * 指定对象必须实现JSONUtilsSerialization接口
	 * @param o
	 * @param fields
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String objectToJsonInclude(Object o, String... fields)
	{
		if(fields.length <= 0){
			return null;
		}
		SimpleFilterProvider fProvider = new SimpleFilterProvider();
		//object must implements JSONUtilsSerialization interface
		fProvider.addFilter("fieldFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		ObjectMapper mapper = new ObjectMapper();
		mapper.setFilters(fProvider);
		String json = null;
		try {
			json = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			logger.error("Json处理异常",e);
		}
		return json;
	}

	/**
	 * 对象串行化，但是排除序列化fields参数中指定的字段
	 *  指定对象必须实现JSONUtilsSerialization接口
	 * @param o
	 * @param fields
	 * @return
	 */
	public static String objectToJsonExclude(Object o, String... fields)
	{
		ObjectMapper mapper = new ObjectMapper();
		// Exclude Null Fields
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		FilterProvider fProvider = new SimpleFilterProvider().addFilter(
				"fieldFilter",
				SimpleBeanPropertyFilter.serializeAllExcept(fields));
		mapper.setFilters(fProvider);
		String json = null;
		try {
			json = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			logger.error("Json处理异常",e);
		}
		return json;
	}

	/**
	 * 从json串构建一个指定类型的类对象实例
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T fromJson(String json,Class<T> valueType){
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		T obj = null;
		try {
			//针对json不认识的字段忽略掉，不要报错
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			obj = om.readValue(json, valueType);
		} catch (JsonParseException e) {
			logger.error("Json解析异常",e);
		} catch (JsonMappingException e) {
			logger.error("JsonMapping异常",e);
		} catch (IOException e) {
			logger.error("IO异常",e);
		}
		return obj;
	}

	/**
	 * Map转JSON
	 * @param map
	 * @return
	 */
	public static String map2json(Map<String, Object> map) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			logger.error("Json转换异常",e);
		} catch (JsonMappingException e) {
			logger.error("JsonMapping转换异常",e);
		} catch (IOException e) {
			logger.error("IO异常",e);
		}

		return json;
	}

	/**
	 * 获取指定json串中的某一个属性的json子串
	 * if whole json is {a:1,person:{id:'111'}}
	 * but just need person sub json string  {id:'111'}  , u can using the method:
	 * getFieldJsonFromJson(json,'person')
	 * fieldName支持子串的子串  比如session.person可以获取到{a:1,session:{id:'',person:{id:''}}
	 * @param json
	 * @param fieldName
	 * @return
	 * 		sub json
	 */
	public static String getFieldJsonFromJson(String json,String fieldName){
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try {
			JsonNode actualObj = mapper.readValue(json, JsonNode.class);
			JsonNode jn = null;
			if(fieldName.indexOf(".") >= 0){
				String fieldNames[] = fieldName.split("\\.");
				JsonNode iteratorJsonNode = actualObj;
				for(int i=0;i<fieldNames.length;i++){
					iteratorJsonNode = iteratorJsonNode.get(fieldNames[i]);
					if(iteratorJsonNode == null)
						break;
				}
				jn = iteratorJsonNode;
			}else{
				jn = actualObj.get(fieldName);
			}
			if(jn != null){
				result = jn.asText();
			}
		} catch (IOException e) {
			logger.error("IO异常",e);
		}
		return result;
	}

	/**
	 * 获取指定json的某一个属性节点对象
	 * @param json
	 * @param fieldName
	 * @return
	 */
	public static JsonNode getFieldJsonNodeFromJson(String json, String fieldName){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode result = null;
		try {
			JsonNode actualObj = mapper.readValue(json, JsonNode.class);
			JsonNode jn = null;
			if(fieldName.indexOf(".") >= 0){
				String fieldNames[] = fieldName.split("\\.");
				JsonNode iteratorJsonNode = actualObj;
				for(int i=0;i<fieldNames.length;i++){
					iteratorJsonNode = iteratorJsonNode.get(fieldNames[i]);
					if(iteratorJsonNode == null)
						break;
				}
				jn = iteratorJsonNode;
			}else{
				jn = actualObj.get(fieldName);
			}
			if(jn != null){
				result = jn;
			}
		} catch (IOException e) {
			logger.error("IO异常",e);
		}
		return result;
	}


	/**
	 * 获取json中相同key的valList
	 * @param json
	 * @param fieldName
	 * @return
	 */
	public static List<JsonNode> getFieldValListFromJson(String json, String fieldName){
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> result = new ArrayList<>();
		try {
			JsonNode actualObj = mapper.readValue(json, JsonNode.class);
			Iterator<JsonNode> iterator = actualObj.iterator();
			while(iterator.hasNext()){
				JsonNode next = iterator.next();
				JsonNode fieldVal = next.get(fieldName);
				result.add(fieldVal);
			}
		} catch (IOException e) {
			logger.error("IO异常",e);
		}
		return result;

	}

	public static void main(String[] args) {
//		String json = "{\"id\":\"433878316e6cb4bbf81641952232ba55\",\"eventName\":\"com.hundun.framework.mq.event.TalkingUploadSuccessEvent\",\"timestamp\":1553080455662,\"talkingId\":\"12341234\",\"topicName\":\"elearning_talking\"}";
//		JsonNode jn = JSONUtils.getFieldJsonNodeFromJson(json, "eventName");
//		System.out.println(jn.asText());

//		String json = "[{\"id\":\"2853\",\"title\":\"前端运营纵线\"},{\"id\":\"4848\",\"title\":\"前端运营纵线-SOP-用户运营中心-SOP-语音质检组\"},{\"id\":\"4851\",\"title\":\"前端运营纵线-SOP-用户运营中心-SOP-培训运营组\"},{\"id\":\"4993\",\"title\":\"前端运营纵线-SOP-用户运营中心-SOP-新媒体质检组\"}]";
//		List<JsonNode> list = JSONUtils.getFieldValListFromJson(json, "id");
//		System.out.println(list);

//		String resultStr = "{\"code\":200,\"msg\":\"成功\",\"data\":{\"currentTaskDefinitionKey\":\"submitForm\",\"nextAssignee\":\"zhangqi\"}}";
//		JsonNode nextAssignee = JSONUtils.getFieldJsonNodeFromJson(resultStr, "data.nextAssignee");
//		JsonNode currNodeType = JSONUtils.getFieldJsonNodeFromJson(resultStr, "data.currentTaskDefinitionKey");
//		String nodeTypeStr = nextAssignee == null ? null : nextAssignee.asText().trim();
//		String currNodeTypeStr = currNodeType == null ? null : currNodeType.textValue();
//		System.out.println(nodeTypeStr + "---" + currNodeTypeStr);

//		String str = "{\"code\":200,\"msg\":\"成功\",\"data\":\"创建成功\"}";
//		String code = JSONUtils.getFieldJsonFromJson(str, "code");
//		System.out.println(code);

		//转换成对象
		String courseListStr = "{\n" +
				"\"data\":{\"course_num\":279,\"course_list\":[{\"left_top_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/fd53df73c68a113de52751f103ac4980\",\"action_num\":\"\",\"standard_school_time\":\"2020-02-22 09:00:00\",\"course_score\":0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"青岛酷特智能   总裁\",\"teacher_name\":\"张蕴蓝\",\"title\":\"酷特智能：C2M传统制造业转型探索之路\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"878209e48ab85b52ed064c000e0d480a\",\"play_stat\":\"\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-02-22 09:00:00\",\"state_display\":[\"立即预约\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":1,\"silent1x2_ratio\":2.39285714,\"title\":\"酷特智能：C2M传统制造业转型探索之路\",\"state_control\":7,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/bea3a362dd4eefb6d22b34051536e80d.png\",\"watch_times\":\"\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"张蕴蓝\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/1e5044dbb888f01859787eec44018b88.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/bea3a362dd4eefb6d22b34051536e80d.png\",\"price\":\"入学观看\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":0,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/0d5766d2ef2f7f13fab55eb2d9b04b06.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/94f66ef2d88178164115bf76f51c1a25.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月22日 09:00-10:00\",\"teacher_position\":\"青岛酷特智能   总裁\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":0,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"青岛酷特智能   总裁\",\"teacher_id\":\"401d8cbb6c69c987e9bfec84e0c4a926\",\"teacher_name\":\"张蕴蓝\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/94d24427eb622f3031369d16be4e9abf.png\"}],\"time\":\"2020-02-22\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/bea3a362dd4eefb6d22b34051536e80d.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":0,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/94d24427eb622f3031369d16be4e9abf.png\"},{\"left_top_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/fd53df73c68a113de52751f103ac4980\",\"action_num\":\"\",\"standard_school_time\":\"2020-02-15 09:00:00\",\"course_score\":0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"BV百度风投CEO\",\"teacher_name\":\"刘维\",\"title\":\"AI时代的产业效率革命\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"6298f46d7a8198f78a430d4f9958a2ce\",\"play_stat\":\"\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-02-15 09:00:00\",\"state_display\":[\"立即预约\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":1,\"silent1x2_ratio\":2.39285714,\"title\":\"AI时代的产业效率革命\",\"state_control\":7,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/ddf4f1da938ee79cfca084d081189ced.png\",\"watch_times\":\"\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"刘维\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/1c2e400cb61fd978bcccba9f6475a4f2.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/ddf4f1da938ee79cfca084d081189ced.png\",\"price\":\"入学观看\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":0,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/7e457f90fe35d2d7a38a7150e29a3426.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/31ccf4578f5ef6793ad4acc610562641.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月15日 09:00-10:30\",\"teacher_position\":\"BV百度风投CEO\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":0,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"BV百度风投CEO\",\"teacher_id\":\"e4b3cd94496a15f8e4ad00c8213ee2ce\",\"teacher_name\":\"刘维\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/890d84188063e7de642bed02dff285ec.png\"}],\"time\":\"2020-02-15\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/ddf4f1da938ee79cfca084d081189ced.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":0,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/890d84188063e7de642bed02dff285ec.png\"},{\"left_top_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/fd53df73c68a113de52751f103ac4980\",\"action_num\":\"\",\"standard_school_time\":\"2020-02-13 20:00:00\",\"course_score\":0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"熊猫传媒创始人\",\"teacher_name\":\"申晨\",\"title\":\"文化创新赋能产品：后病毒时代的营销之道\"}],\"live_type\":1,\"course_status\":1,\"is_buy\":0,\"course_id\":\"227764f8b9cf6fd677bb23c6cffda3cb\",\"play_stat\":\"\",\"allow_play_title\":\"\",\"duration\":\"2020-02-13 20:00:00\",\"state_display\":[\"立即预约\"],\"sku_shadow_image\":\"\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"文化创新赋能产品：后病毒时代的营销之道\",\"state_control\":7,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/432c6f3f52fa3422e0e7e7c5b6ce87da.png\",\"watch_times\":\"\",\"recommended_language\":\"\",\"allow_download\":1,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"申晨\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/aa7f75e23577adf00d2c5153e7b6eb0c.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/432c6f3f52fa3422e0e7e7c5b6ce87da.png\",\"price\":\"\",\"sku_name\":\"研习社\",\"allow_play\":1,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/99ac09e4f6f4927b54dd47380969d7d1.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/fa108737aab1abdc79f524e04c588d46.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月13日 20:00-21:30\",\"teacher_position\":\"熊猫传媒创始人\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":0,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"熊猫传媒创始人\",\"teacher_id\":\"b501aaafcc92972f971206308f6f94cd\",\"teacher_name\":\"申晨\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/966931fa135a4787378b65260994e1fc.png\"}],\"time\":\"2020-02-13\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/432c6f3f52fa3422e0e7e7c5b6ce87da.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":0,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/966931fa135a4787378b65260994e1fc.png\"},{\"left_top_image\":\"\",\"action_num\":\"9.9万人次\",\"standard_school_time\":\"2020-02-11 20:00:00\",\"course_score\":5.0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"峰瑞资本创始合伙人\",\"teacher_name\":\"李丰\",\"title\":\"疫情过后“好公司”时代来临，谁会被淘汰、谁会受青睐？ \"}],\"live_type\":1,\"course_status\":1,\"is_buy\":0,\"course_id\":\"1102675d114323df7820df4bbcab6272\",\"play_stat\":\"9.9万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-02-11 20:00:00\",\"state_display\":[\"最新\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"疫情过后“好公司”时代来临，谁会被淘汰、谁会受青睐？ \",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"3人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/18dda2b697fddfcde10b7ad8402e3457.png\",\"watch_times\":\"9.9万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"李丰\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/af23c3f0a16cf82e21213dda8dc97afb.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/18dda2b697fddfcde10b7ad8402e3457.png\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/c64453fb9d9c8028a8cbed630a8904a3.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/1016489a21b10f6173d934245a022428.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月11日 20:00-21:30\",\"teacher_position\":\"峰瑞资本创始合伙人\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"峰瑞资本创始合伙人\",\"teacher_id\":\"660734dddcf06f026747dfdc4d9c9c29\",\"teacher_name\":\"李丰\",\"teacher_head_image\":\"http://yxs-app.oss-cn-beijing.aliyuncs.com/4c493be299e3203455ea97a0f2c0de52\"}],\"time\":\"2020-02-11\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/18dda2b697fddfcde10b7ad8402e3457.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"http://yxs-app.oss-cn-beijing.aliyuncs.com/4c493be299e3203455ea97a0f2c0de52\"},{\"left_top_image\":\"\",\"action_num\":\"6.5万人次\",\"standard_school_time\":\"2020-02-10 20:00:00\",\"course_score\":5.0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"秋叶PPT创始人，秋叶商学院创始人\",\"teacher_name\":\"秋叶大叔\",\"title\":\"铁肩担道义，危急时刻如何塑造企业家IP\"}],\"live_type\":1,\"course_status\":1,\"is_buy\":0,\"course_id\":\"d17a8b1578b01e92d49c7ce45eb72cfd\",\"play_stat\":\"6.5万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-02-10 20:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"铁肩担道义，危急时刻如何塑造企业家IP\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/53d50a23f6ad39e235ab1e9066044f1e.png\",\"watch_times\":\"6.5万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"秋叶大叔\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64b770f85a3ea85a87d83c48f44c8106.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/53d50a23f6ad39e235ab1e9066044f1e.png\",\"price\":\"128元/150研值\",\"sku_name\":\"\",\"allow_play\":0,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/08d736fc98b4462904917d0b653d40bb.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/c80f9e841a2eef210fcf703ee2476572.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月10日 20:00-21:30\",\"teacher_position\":\"秋叶PPT创始人，秋叶商学院创始人\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"\",\"teacher_list\":[{\"teacher_position\":\"秋叶PPT创始人，秋叶商学院创始人\",\"teacher_id\":\"c8235c27a8fbe57e6707ce8c4fd9a862\",\"teacher_name\":\"秋叶大叔\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/6e4ac4c867cdc5dde747403b631fe888.png\"}],\"time\":\"2020-02-10\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/53d50a23f6ad39e235ab1e9066044f1e.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/6e4ac4c867cdc5dde747403b631fe888.png\"},{\"left_top_image\":\"\",\"action_num\":\"11万人次\",\"standard_school_time\":\"2020-02-08 09:00:00\",\"course_score\":4.7,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"神策数据创始人兼CEO\",\"teacher_name\":\"桑文锋\",\"title\":\"如何构建To B公司的运作体系\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"fcf8033b670d9019f5a0c124fb355a31\",\"play_stat\":\"11万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-02-08 09:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":1,\"silent1x2_ratio\":2.39285714,\"title\":\"如何构建To B公司的运作体系\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"53人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64f24f72945fce4d8d3b81f83124324f.png\",\"watch_times\":\"11万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"桑文锋\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d7580328e8707efc99fdde189066c849.png\",\"note_article_id\":\"85cca57e7a002eb725a9303ba78390e9\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64f24f72945fce4d8d3b81f83124324f.png\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/73ff705b71848783bcd390f5f787311d.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d829605122f0322e12d83af488daa24a.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月08日 09:00-10:00\",\"teacher_position\":\"神策数据创始人兼CEO\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"神策数据创始人兼CEO\",\"teacher_id\":\"bfef795aa6c83e2a9b92df0e172d5236\",\"teacher_name\":\"桑文锋\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/df5103242e1436f7a8144f79e95c71e8.png\"}],\"time\":\"2020-02-08\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64f24f72945fce4d8d3b81f83124324f.png\",\"tag_list\":[\"产品设计\",\"大数据\"],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/df5103242e1436f7a8144f79e95c71e8.png\"},{\"left_top_image\":\"\",\"action_num\":\"7.3万人次\",\"standard_school_time\":\"2020-02-07 20:00:00\",\"course_score\":4.9,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"首都经贸大学教授\",\"teacher_name\":\"陈立平\",\"title\":\"危中寻机？被重创的零售业自救指南\"}],\"live_type\":1,\"course_status\":1,\"is_buy\":0,\"course_id\":\"b2cf0cbdd2c6f6655100bdc18d10f0b5\",\"play_stat\":\"7.3万\",\"allow_play_title\":\"\",\"duration\":\"2020-02-07 20:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"危中寻机？被重创的零售业自救指南\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/ba62a343ae9913b7e5b547a1e71a7777.png\",\"watch_times\":\"7.3万次播放\",\"recommended_language\":\"\",\"allow_download\":1,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"陈立平\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/6a78227a081d73ff134a7d34284af04d.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/ba62a343ae9913b7e5b547a1e71a7777.png\",\"price\":\"\",\"sku_name\":\"\",\"allow_play\":1,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/6b209686d3d9e3aa0c3927b5276f2271.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/688b03c7bd8556be2661393a562e925f.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月07日 20:00-21:00\",\"teacher_position\":\"首都经贸大学教授\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"\",\"teacher_list\":[{\"teacher_position\":\"首都经贸大学教授\",\"teacher_id\":\"6dd7e376c7183eeb5a2b7c0bc00a0b14\",\"teacher_name\":\"陈立平\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/dfdffde92e4b36cfcceab2db061ef60b.png\"}],\"time\":\"2020-02-07\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/ba62a343ae9913b7e5b547a1e71a7777.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/dfdffde92e4b36cfcceab2db061ef60b.png\"},{\"left_top_image\":\"\",\"action_num\":\"4.3万人次\",\"standard_school_time\":\"2020-02-06 20:00:00\",\"course_score\":5.0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"NeuroEdge 脑优势领导力中心的创始人，伦敦大学组织心理学硕士\",\"teacher_name\":\"Elise Zhu\",\"title\":\"如何在失序中提升心理复原力\"}],\"live_type\":1,\"course_status\":1,\"is_buy\":0,\"course_id\":\"c481c11ab587cd1c56868dab0cafe105\",\"play_stat\":\"4.3万\",\"allow_play_title\":\"\",\"duration\":\"2020-02-06 20:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"如何在失序中提升心理复原力\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d7f2da63a2ed2f462c091a0fae072dd8.png\",\"watch_times\":\"4.3万次播放\",\"recommended_language\":\"\",\"allow_download\":1,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"Elise Zhu\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/8d17c25f772ae45eb8cf08d7de9ab504.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d7f2da63a2ed2f462c091a0fae072dd8.png\",\"price\":\"\",\"sku_name\":\"\",\"allow_play\":1,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/7fb620e652dbbe1c806ee59ad872ac31.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/1161fae387b7592a48fff8b5a0950f5b.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月06日 20:00-21:30\",\"teacher_position\":\"NeuroEdge 脑优势领导力中心的创始人，伦敦大学组织心理学硕士\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"\",\"teacher_list\":[{\"teacher_position\":\"NeuroEdge 脑优势领导力中心的创始人，伦敦大学组织心理学硕士\",\"teacher_id\":\"c6cbdd16560549435d5b3b157056270a\",\"teacher_name\":\"Elise Zhu\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/532f7d52906965f746e286c9793c7af8.png\"}],\"time\":\"2020-02-06\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d7f2da63a2ed2f462c091a0fae072dd8.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/532f7d52906965f746e286c9793c7af8.png\"},{\"left_top_image\":\"\",\"action_num\":\"4.1万人次\",\"standard_school_time\":\"2020-02-06 14:00:00\",\"course_score\":5.0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"杏树林首席医学官，新健康人力资本研究院院长\",\"teacher_name\":\"朱祖懿\",\"title\":\"防控新冠肺炎的企业行动指南\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"d29323a8d1ae31eef5e5f6debd7b79d5\",\"play_stat\":\"4.1万\",\"allow_play_title\":\"\",\"duration\":\"2020-02-06 14:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"防控新冠肺炎的企业行动指南\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/84d3cf7d13c67e472293b4f7025dedfc.png\",\"watch_times\":\"4.1万次播放\",\"recommended_language\":\"\",\"allow_download\":1,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"朱祖懿\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d89e0844aaa4fd4df48cc18e7b10e2fb.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/84d3cf7d13c67e472293b4f7025dedfc.png\",\"price\":\"\",\"sku_name\":\"\",\"allow_play\":1,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b7730b76fc186526dde114defdfeda75.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/f13db58efd2e585ecfe64ae8928c47fd.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月06日 14:00-15:00\",\"teacher_position\":\"杏树林首席医学官，新健康人力资本研究院院长\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"\",\"teacher_list\":[{\"teacher_position\":\"杏树林首席医学官，新健康人力资本研究院院长\",\"teacher_id\":\"323476d24c38bb8aece4cfc20dc46862\",\"teacher_name\":\"朱祖懿\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/38712691e6752440032b946a79430a4c.png\"}],\"time\":\"2020-02-06\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/84d3cf7d13c67e472293b4f7025dedfc.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/38712691e6752440032b946a79430a4c.png\"},{\"left_top_image\":\"\",\"action_num\":\"10万人次\",\"standard_school_time\":\"2020-02-01 09:00:00\",\"course_score\":4.7,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"柠萌影业联合创始人、CEO\",\"teacher_name\":\"陈菲\",\"title\":\"《小欢喜》背后，群智涌现的组织\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"bed22c5672087fb29dee6ccbf8d406ab\",\"play_stat\":\"10万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-02-01 09:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":1,\"silent1x2_ratio\":2.39285714,\"title\":\"《小欢喜》背后，群智涌现的组织\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"71人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/5fa36e6129abd05a71320cf91e29f1a1.png\",\"watch_times\":\"10万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"陈菲\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/7b9db1f83a5529862fbb660c3cb8f9ed.png\",\"note_article_id\":\"3697b790f0c535b495e3e4e1c7ce91f1\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/5fa36e6129abd05a71320cf91e29f1a1.png\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":0,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/f312c71b104353fcddc1eaa7d3d03f9f.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/59c09bf9087216cf599de9f540878dbb.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"02月01日 09:00-10:30\",\"teacher_position\":\"柠萌影业联合创始人、CEO\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"柠萌影业联合创始人、CEO\",\"teacher_id\":\"8167627028bcbc6aafa7fc0591a60a21\",\"teacher_name\":\"陈菲\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/c6a62a81defb1bd3368205a849fddec7.png\"}],\"time\":\"2020-02-01\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/5fa36e6129abd05a71320cf91e29f1a1.png\",\"tag_list\":[\"组织管理\",\"内容经济\",\"使命愿景\"],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/c6a62a81defb1bd3368205a849fddec7.png\"},{\"left_top_image\":\"\",\"action_num\":\"9.0万人次\",\"standard_school_time\":\"2020-01-25 00:00:00\",\"course_score\":5.0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"好课陪你过春节\",\"teacher_name\":\"混沌大学\",\"title\":\"春节好课推荐，陪大家一起不出门过春节\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"cdc509140245945fe804e8fcaa66e93c\",\"play_stat\":\"9.0万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-01-25 00:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"春节好课推荐，陪大家一起不出门过春节\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"0人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/0645b54d02223cb06934384a22d47d60.jpg\",\"watch_times\":\"9.0万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"混沌大学\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/0278a1da8c81b25e3de2c33e5baf57aa.jpg\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/0645b54d02223cb06934384a22d47d60.jpg\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":0,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/676b32ceabd41cb5932ce27fdf5e38ae.jpg\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/cc2a53bf2a5318475fd92c697e27ad66.jpg\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"01月25日 00:00-00:00\",\"teacher_position\":\"好课陪你过春节\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"好课陪你过春节\",\"teacher_id\":\"aa5ea8cd98c1c390727a9647eb928cce\",\"teacher_name\":\"混沌大学\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/2b007dfe49f64dba0f015cfdb15c4208.jpg\"}],\"time\":\"2020-01-25\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/0645b54d02223cb06934384a22d47d60.jpg\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/2b007dfe49f64dba0f015cfdb15c4208.jpg\"},{\"left_top_image\":\"\",\"action_num\":\"4.4万人次\",\"standard_school_time\":\"2020-01-22 00:00:00\",\"course_score\":5.0,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"\",\"teacher_name\":\"混沌大学\",\"title\":\"春节特别企划：研习社好课榜单\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"4e6c9ff5d639826a48965428456456b8\",\"play_stat\":\"4.4万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-01-22 00:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":0,\"silent1x2_ratio\":2.39285714,\"title\":\"春节特别企划：研习社好课榜单\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"10人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64461be7e40c3c4f32f06dde72cbaeab.png\",\"watch_times\":\"4.4万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"混沌大学\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/f902e7873585f9e0458976dc9fceb72c.png\",\"note_article_id\":\"0\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64461be7e40c3c4f32f06dde72cbaeab.png\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":0,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64ad668ec61166b1e319b3c432790209.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/1d5eebb03ac060afa09f9f86a7b7a598.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"01月22日 00:00-01:00\",\"teacher_position\":\"\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"\",\"teacher_id\":\"6cc2c0dadd1a955b43962092ccf4542a\",\"teacher_name\":\"混沌大学\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/032b743aca4f6206a589f195cdffba97.png\"}],\"time\":\"2020-01-22\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/64461be7e40c3c4f32f06dde72cbaeab.png\",\"tag_list\":[],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/032b743aca4f6206a589f195cdffba97.png\"},{\"left_top_image\":\"\",\"action_num\":\"9.2万人次\",\"standard_school_time\":\"2020-01-18 09:00:00\",\"course_score\":4.6,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"复星康养董事长兼CEO\",\"teacher_name\":\"董岩\",\"title\":\"复星康养：老龄化时代的养⽼产业探索\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"7af3ea39a95f88faf2a3a9493aaa2887\",\"play_stat\":\"9.2万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-01-18 09:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":1,\"silent1x2_ratio\":2.39285714,\"title\":\"复星康养：老龄化时代的养⽼产业探索\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"80人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/a109e99ab17082b649cfd1e116191cf6.png\",\"watch_times\":\"9.2万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"董岩\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/22f4d08d8b0467586041f51eedae757c.png\",\"note_article_id\":\"142e3dfdf8479d698766398e2e183c5a\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/a109e99ab17082b649cfd1e116191cf6.png\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":0,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/4b589b48b565eb88d1ae9e3601194478.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/91a4f04c11f65e72ee697dea6c6af986.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"01月18日 09:00-10:00\",\"teacher_position\":\"复星康养董事长兼CEO\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"复星康养董事长兼CEO\",\"teacher_id\":\"072298475ff4c5edd48a03cdc35aec62\",\"teacher_name\":\"董岩\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/2b6dd8ffeb301958f1384a34416bc982.png\"}],\"time\":\"2020-01-18\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/a109e99ab17082b649cfd1e116191cf6.png\",\"tag_list\":[\"盈利模式\",\"用户运营\",\"复星\"],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/2b6dd8ffeb301958f1384a34416bc982.png\"},{\"left_top_image\":\"\",\"action_num\":\"13万人次\",\"standard_school_time\":\"2020-01-11 09:00:00\",\"course_score\":4.8,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"玛丽黛佳品牌创始人、知名策展人\",\"teacher_name\":\"崔晓红\",\"title\":\"玛丽黛佳：产品原创力，重新定义国货之美\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"c4007a338c1c63994fa5afa017396f01\",\"play_stat\":\"13万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-01-11 09:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":1,\"silent1x2_ratio\":2.39285714,\"title\":\"玛丽黛佳：产品原创力，重新定义国货之美\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"121人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/3568fe42210aae41772381218fd5d48f.png\",\"watch_times\":\"13万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"崔晓红\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/11a10238ec797afac0d78169e87ae4db.png\",\"note_article_id\":\"9fee2b658e931889d8fecad2e58a3d89\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/3568fe42210aae41772381218fd5d48f.png\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":1,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/9db21d0e5e5884c1b95c57bc11151393.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/aaf29e257a96113191afdb871a88967d.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"01月11日 09:00-11:00\",\"teacher_position\":\"玛丽黛佳品牌创始人、知名策展人\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"玛丽黛佳品牌创始人、知名策展人\",\"teacher_id\":\"a6f3024d58418e8d1244d4e6a134da55\",\"teacher_name\":\"崔晓红\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/5d4262336992e6747e804f9114a4a576.png\"}],\"time\":\"2020-01-11\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/3568fe42210aae41772381218fd5d48f.png\",\"tag_list\":[\"产品设计\",\"创造力\",\"场景切片法\"],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/5d4262336992e6747e804f9114a4a576.png\"},{\"left_top_image\":\"\",\"action_num\":\"7.4万人次\",\"standard_school_time\":\"2020-01-04 09:00:00\",\"course_score\":4.4,\"course_duration\":\"\",\"multi_titles\":[{\"teacher_position\":\"VSPN CEO\",\"teacher_name\":\"应书岭\",\"title\":\"借电竞力量，激活新兴消费市场\"}],\"live_type\":0,\"course_status\":1,\"is_buy\":0,\"course_id\":\"f2b5d3c3e4d29f4769bfc65ebfcafe54\",\"play_stat\":\"7.4万\",\"allow_play_title\":\"可试看\",\"duration\":\"2020-01-04 09:00:00\",\"state_display\":[\"\"],\"sku_shadow_image\":\"http://yxs-pic.oss-cn-beijing.aliyuncs.com/f2adf822625908f35a468091649d2083\",\"is_open_stake\":1,\"silent1x2_ratio\":2.39285714,\"title\":\"借电竞力量，激活新兴消费市场\",\"state_control\":6,\"purchase_url\":\"\",\"buy_time\":\"1970-01-01\",\"sub_title\":\"\",\"buy_stat\":\"14人已购买\",\"parent_id\":\"\",\"has_shared\":0,\"square_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b69cd74fc0c899b96d7b4372fcdb83a8.png\",\"watch_times\":\"7.4万次播放\",\"recommended_language\":\"\",\"allow_download\":0,\"series_info\":{},\"sku_circle_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/d39125e2e397ad3b8c3771de80fe7043.png\",\"sku_mode\":\"yxs\",\"teacher_name\":\"应书岭\",\"cover_image_2x1\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/013ae949ce605e6aecc162eda39d3b88.png\",\"note_article_id\":\"0c6362312d0fb92516c6311635cc5bec\",\"has_bespeak\":0,\"yzmall_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b69cd74fc0c899b96d7b4372fcdb83a8.png\",\"price\":\"128元/150研值\",\"sku_name\":\"研习社\",\"allow_play\":0,\"is_display_ask\":0,\"cover_image_v2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/c20a4209704b7ee1410ce2b16daa7979.png\",\"is_special_lesson\":0,\"cover_image_1x2\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/8443c01ae3fb2eae7b253017168252ec.png\",\"lesson_subject\":\"混沌大学 \",\"is_display_reward\":0,\"school_time\":\"01月04日 09:00-10:00\",\"teacher_position\":\"VSPN CEO\",\"zxjy_info\":{},\"is_elective\":0,\"example_info\":{\"is_example_course\":0,\"interact_switch\":0},\"type\":100,\"is_cxy_course\":0,\"practice_id\":\"\",\"tag_name\":\"研习社\",\"teacher_list\":[{\"teacher_position\":\"VSPN CEO\",\"teacher_id\":\"bba31999830fc15682f258e2690725da\",\"teacher_name\":\"应书岭\",\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/4a0a010c4853a0aae8af681efbc4e44f.png\"}],\"time\":\"2020-01-04\",\"cover_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b69cd74fc0c899b96d7b4372fcdb83a8.png\",\"tag_list\":[\"商业模式\",\"市场研究\",\"品牌营销\"],\"watch_icon\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/b651ae95a80159a8e8c7fc5e89751d55.png\",\"origin_type\":100,\"teacher_head_image\":\"https://yxs-web.oss-cn-beijing.aliyuncs.com/4a0a010c4853a0aae8af681efbc4e44f.png\"}]}}";
				JsonNode data = JSONUtils.getFieldJsonNodeFromJson(courseListStr, "data");
		JsonNode courseNum = JSONUtils.getFieldJsonNodeFromJson(courseListStr, "data.course_num");
		JsonNode course_list = JSONUtils.getFieldJsonNodeFromJson(courseListStr, "data.course_list");

//		JsonNode data = JSONUtils.getFieldJsonNodeFromJson(courseListStr, "data");
//		JsonNode courseNum = JSONUtils.getFieldJsonNodeFromJson(courseListStr, "data.course_num");
//		String course_list = JSONUtils.getFieldJsonFromJson(courseListStr, "data.course_list");
		String s = JSONUtils.objectToJson(course_list);
		List<CourseBaseDTO> courseBaseDTOS = JSONUtils.jsonToList(s, CourseBaseDTO.class);

		System.out.println(courseBaseDTOS);
	}


}
