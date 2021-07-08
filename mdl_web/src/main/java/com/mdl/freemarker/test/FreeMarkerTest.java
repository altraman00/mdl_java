package com.mdl.freemarker.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mdl.freemarker.FreeMarkerUtils;

import freemarker.template.TemplateException;

public class FreeMarkerTest {

	public static void main(String[] args) throws TemplateException {
		
		FreeMarkerUtils utl = new FreeMarkerUtils();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("size", 0);
		map.put("gte_val", "1497283200000");
		map.put("lte_val", "1497928996980");
		map.put("min_val", "1497283200000");
		map.put("max_val", "1497928996980");
		map.put("interval", "21526566ms");
		
		try {
			String content =  utl.getHtmlContent("/files/ftl", "FMemail.ftl", map);
			System.out.println("返回的json" + "\n" + content + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
