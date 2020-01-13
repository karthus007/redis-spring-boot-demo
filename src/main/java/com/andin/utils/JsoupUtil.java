package com.andin.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * xss注入清除
 * @author Administrator
 *
 */
public class JsoupUtil {
	
    private static final Whitelist whitelist = Whitelist.basicWithImages();
    
    /** 配置过滤化参数,不对代码进行格式化 */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    
    static {
        // 所以需要给所有标签添加style属性
        whitelist.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
    
    public static void main(String[] args) throws Exception {
        String text = "<a href=\"http://www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss";
        System.out.println(clean(text));
    }

}
