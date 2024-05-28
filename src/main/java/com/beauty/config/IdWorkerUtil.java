package com.beauty.config;

import com.baomidou.mybatisplus.toolkit.IdWorker;

/**
 * 唯一ID生成
 */
public class IdWorkerUtil {

    public static final String getId(){
        return "beauty"+ IdWorker.getId();
    }
}
