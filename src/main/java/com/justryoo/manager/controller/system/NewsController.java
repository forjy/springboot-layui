package com.justryoo.manager.controller.system;

import com.justryoo.manager.listener.ShiroSessionLinstener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 新闻发布
 */
@Controller
@RequestMapping("news")
public class NewsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 添加新闻
     * @return
     */
    @RequestMapping("/add")
    public String toPage() {
        logger.info("添加新闻");
        return "/news/add";
    }

    @RequestMapping("/online")
    @ResponseBody
    public Object online(HttpServletRequest request) {
        return  "当前在线人数：" + ShiroSessionLinstener.online + "人";
    }
}
