package com.andin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andin.redis.RedisUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Resource
	private RedisUtil redisUtil;

	@RequestMapping("/app")
	@ResponseBody
	public Map<String, Object> getAppInfo(@RequestParam("name") String name){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			redisUtil.set("name", name);
			String app = redisUtil.get("name");
			map.put("data", app);
			map.put("resultMsg", "请求成功");
			map.put("resultStatus", "0000");
			logger.debug("TestController.getAppInfo method execute is successful...");
		} catch (Exception e) {
			map.put("resultMsg", "请求失败");
			map.put("resultStatus", "0001");
			logger.error("TestController.getAppInfo method execute is error: ", e.getMessage());
		}
		return map;
	}
	
	
}
