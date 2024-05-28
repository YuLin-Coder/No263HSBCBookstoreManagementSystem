package com.beauty.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件地址映射
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /** 文件大小限制 */
    private Long maxSize;

    private String url;

    private ElPath mac;

    private ElPath linux;

    private ElPath windows;

    public ElPath getPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith(ElAdminConstant.WIN)) {
            return windows;
        } else if(os.toLowerCase().startsWith(ElAdminConstant.MAC)){
            return mac;
        }
        return linux;
    }

    @Data
    public static class ElPath{

        private String path;

        private String avatar;
    }
}
