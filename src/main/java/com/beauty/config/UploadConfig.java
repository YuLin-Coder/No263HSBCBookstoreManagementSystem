package com.beauty.config;

import com.beauty.config.properties.FileProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 文件路径映射配置
 */
@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter {

    /**
     * 文件配置
     */
    private final FileProperties properties;

    public UploadConfig(FileProperties properties) {
        this.properties = properties;
    }

    /**
     * 将D:\\upload下的文件映射到当前项目/upload/下
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        FileProperties.ElPath path = properties.getPath();
        String pathUtl = "file:" + path.getPath().replace("\\", "/");
        //addResourceHandler()里配置需要映射的文件夹，此处代表映射文件夹user下的所有资源。
        //addResourceLocations()配置文件夹在系统中的路径，使用绝对路径，格式为“file:你的路径”
        registry.addResourceHandler("/files/**").addResourceLocations(pathUtl);
    }
}
