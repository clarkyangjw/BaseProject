package com.clark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {

    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("group1");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("group2");
    }

    @Bean
    public Docket docket(Environment environment) {

        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean flag = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .groupName("Clark") // 配置分组
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口

                //any() // 扫描所有，项目中的所有接口都会被扫描到
                //none() // 不扫描接口
                // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
                //withMethodAnnotation(final Class<? extends Annotation> annotation)
                // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
                //withClassAnnotation(final Class<? extends Annotation> annotation)
                //basePackage(final String basePackage) // 根据包路径扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.clark.controller"))

                // 配置如何通过path过滤(扫描指定请求下的借口),即这里只扫描请求以/clark开头的接口
                //any() // 任何请求都扫描
                //none() // 任何请求都不扫描
                //regex(final String pathRegex) // 通过正则表达式控制
                //ant(final String antPattern) // 通过ant()控制
                //.paths(PathSelectors.ant("/user/**"))

                .build();
    }


    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Clark", "https://github.com/clarkyangjw", "jyang210@myseneca.ca");
        return new ApiInfo(
                "Base Project", // 标题
                "An employee management system", // 描述
                "v1.0", // 版本
                "https://github.com/clarkyangjw", // 组织链接
                contact, // 联系人信息
                "Apach 2.0", // 许可
                "", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
