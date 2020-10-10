1. 继承ZuulFilter然后重写业务方法run
    ```text
    // 第二个Filter
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

    //第三个filter
    public class ThirdPreFilter extends ZuulFilter {
        @Override
        public String filterType() {
            return PRE_TYPE;
        }
        @Override
        public int filterOrder() {
            return 3;
        }
        @Override
        public boolean shouldFilter() {
            RequestContext ctx = RequestContext.getCurrentContext();
            // 从上下文中获取logic-is-success值，用于判断此Filter是否执行
            return (boolean)ctx.get("logic-is-success");
        }
        @Override
        public Object run() throws ZuulException {
            log.info("这是ThirdPreFilter ！");
            // 从RequestContext获取上下文
            RequestContext ctx = RequestContext.getCurrentContext();
            // 从上下文获取HttpServletResponse
            HttpServletRequest request = ctx.getRequest();
            // 从request获取参数b
            String b = request.getParameter("b");
            // 如果参数b为空则进入此逻辑
            if (StringUtils.isBlank(b)){
                // 对该请求禁止路由，也就是禁止访问下游服务
                ctx.setSendZuulResponse(false);
                //设定responseBody供PostFilter使用
                // {"status":500, "message":"b参数为空！"}
                ctx.setResponseBody("{\"status\":500, \"message\":\"b参数为空！\"}");
                //logic-is-success保存于上下文，作为同类下游Filter的执行开关
                // 假定后续还有自定义Filter当设置此值
                ctx.set("logic-is-success", false);
                // 到这里此Filter逻辑结束
            }
            ctx.set("logic-is-success", true);
            return null;
        }
    }
 
    // 第四个Filter
     public class PostFilter extends ZuulFilter {
         @Override
         public String filterType() {
             return POST_TYPE;
         }
         @Override
         public int filterOrder() {
             return 0;
         }
         @Override
         public boolean shouldFilter() {
             return true;
         }
         @Override
         public Object run() throws ZuulException {
             log.info("这是PostFilter ！");
             RequestContext ctx = RequestContext.getCurrentContext();
             // 处理返回中文乱码
             ctx.getResponse().setCharacterEncoding("UTF-8");
             // 获取上下文中保存的responseBody
             String responseBody = ctx.getResponseBody();
             // 如果responseBody不为空,则说明流程有异常发生
             if (responseBody != null){
                 // 设定返回状态码
                 ctx.setResponseStatusCode(500);
                 // 替换响应报文
                 ctx.setResponseBody(responseBody);
             }
             return null;
         }
     }
    ```
2. 注入到spring容器中
    ```text
    @Bean
    public SecondPreFilter secondZuulFilter(){
        return new SecondPreFilter() ;
    }
    @Bean
    public ThirdPreFilter thirdPreFilter(){
        return new ThirdPreFilter() ;
    }
    @Bean
    public PostFilter postFilter(){
        return new PostFilter() ;
    }
    ```