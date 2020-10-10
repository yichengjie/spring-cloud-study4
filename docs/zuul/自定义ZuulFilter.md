1. 继承ZuulFilter然后重写业务方法run
    ```text
    public class FirstPreFilter extends ZuulFilter {
        @Override
        public String filterType() {
            return "pre";
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
            log.info("====> 这是第一个自定义Zuul Filter !");
            return null;
        }
    }
    ```
2. 注入到spring容器中
    ```text
    @Bean
        public FirstPreFilter firstZuulFilter(){
            return new FirstPreFilter() ;
        }
    ```