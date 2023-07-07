package sun.project.to_parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    Docket docketPage(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
        docket.select().apis(RequestHandlerSelectors.basePackage("sun.project.to_parking.controller"))
                .paths(PathSelectors.ant("/page/**")).build();
        docket.groupName("1、页面控制");
        return docket;
    }
    @Bean
    Docket docketAdmin(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
        docket.select().apis(RequestHandlerSelectors.basePackage("sun.project.to_parking.controller"))
                .paths(PathSelectors.ant("/admin/**")).build();
        docket.groupName("2、管理员信息控制层");
        return docket;
    }
    @Bean
    Docket docketUser(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
        docket.select().apis(RequestHandlerSelectors.basePackage("sun.project.to_parking.controller"))
                .paths(PathSelectors.ant("/user/**")).build();
        docket.groupName("3、用户业务处理控制层");
        return docket;
    }
    @Bean
    Docket docketUploadFile(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
        docket.select().apis(RequestHandlerSelectors.basePackage("sun.project.to_parking.controller"))
                .paths(PathSelectors.ant("/uploadFile/**")).build();
        docket.groupName("4、文件上传");
        return docket;
    }
    @Bean
    Docket docketStall(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
        docket.select().apis(RequestHandlerSelectors.basePackage("sun.project.to_parking.controller"))
                .paths(PathSelectors.ant("/stall/**")).build();
        docket.groupName("5、停车位信息控制类");
        return docket;
    }
    @Bean
    Docket docketStallUse(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
        docket.select().apis(RequestHandlerSelectors.basePackage("sun.project.to_parking.controller"))
                .paths(PathSelectors.ant("/stallUse/**")).build();
        docket.groupName("6、停车位预约信息处理控制类");
        return docket;
    }
    Docket docketMqtt(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
        docket.select().apis(RequestHandlerSelectors.basePackage("sun.project.to_parking.mqtt"))
                .paths(PathSelectors.ant("/MqttService/**")).build();
        docket.groupName("7、阿里云物联网车位数据处理");
        return docket;
    }
    /**
     * api的基本信息
     *
     * @return
     */
    public ApiInfo apiInfo() {

        String title = "停车管理系统---服务器后端部分";
        String description = "技术架构：\n" +"springboot"+"+" +"android"+"+"+"stm32"+"\n" +"平台：\n" +"阿里云iot" +"+"+ "阿里云ESC";
        String version = "1.0";
        String termsOfServiceUrl = "null";

        String username = "alansunjin";
        String userUrl = "null";
        String userEmail = "s1025jj@qq.com";

        String licenseUrl = "null";

        String license = "null";

        return new ApiInfo(title, description, version, termsOfServiceUrl, new Contact(username, userUrl, userEmail),
                license,licenseUrl,  new ArrayList<VendorExtension>());
    }
}
