package com.clark.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 添加静态资源文件，外部可以直接访问地址
     * 资源映射路径
     * addResourceHandler：访问映射路径
     * addResourceLocations：资源绝对路径
     *
     * @param registry
     */
    //解决上传完图片后，页面图片没有及时更新的问题，不行的话，在html里图片的src资源链接后加上后面这句，包括加号   +'?<%=new Date()%>'
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/app-assets/images/portrait/small/**")
                .addResourceLocations("file:C:/Clark Software/JavaPersonalProject/general/src/main/resources/static/app-assets/images/portrait/small/");
    }

}


