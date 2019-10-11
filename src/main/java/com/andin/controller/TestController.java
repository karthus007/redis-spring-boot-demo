package com.andin.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Part;

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
			map.put("resultCode", "0000");
			logger.debug("TestController.getAppInfo method execute is successful...");
		} catch (Exception e) {
			map.put("resultMsg", "请求失败");
			map.put("resultCode", "0001");
			logger.error("TestController.getAppInfo method execute is error: ", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/file")
	@ResponseBody
	public Map<String, Object> getAppInfo(@RequestParam("file") Part part){
		logger.debug("TestController.getAppInfo method execute is start...");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			InputStream in = part.getInputStream();
			FileOutputStream fos = new FileOutputStream("D://jj.eml");
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.close();
			map.put("resultMsg", "请求成功");
			map.put("resultCode", "0000");
			logger.debug("TestController.getAppInfo method execute is successful...");
		} catch (Exception e) {
			map.put("resultMsg", "请求失败");
			map.put("resultCode", "0001");
			logger.error("TestController.getAppInfo method execute is error: ", e.getMessage());
		}
		return map;
	}
	
}
