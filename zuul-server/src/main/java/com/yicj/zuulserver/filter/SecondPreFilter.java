package com.yicj.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class SecondPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 通过 ctx.setSendZuulResponse(false) 来禁止routeFilter路由此次请求
    // 通过 ctx.setResponseBody("{\"status\":500, \"message\":\"a参数为空！\"}") 来定制返回结果
    // 通过 ctx.set("logic-is-success", false) 来关联下一个ThirdPreFilter,如果a未传则ThirdPreFilter没有执行的必要
    @Override
    public Object run() throws ZuulException {
        log.info("这是SecondPreFilter!");
        // 从RequestContext中获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 从上下文中获取HttpServletRequest
        HttpServletRequest request = ctx.getRequest();
        // 从request尝试获取参数a
        String a = request.getParameter("a");
        // 如果a参数为空则进入此逻辑
        if (StringUtils.isBlank(a)){
            //对该请求禁止路由，也就是禁止访问下游服务
            ctx.setSendZuulResponse(false);
            // 设定responseBody供PostFilter使用
            // {"status":500, "message":"a参数为空！"}
            ctx.setResponseBody("{\"status\":500, \"message\":\"a参数为空！\"}");
            // logic-is-success保存于上下文，作为同类型下游Filter的执行开关
            ctx.set("logic-is-success", false);
            // 到这里此Filter逻辑结束
            return null ;
        }
        // 设置避免报空
        ctx.set("logic-is-success", true);
        return null;
    }
}
