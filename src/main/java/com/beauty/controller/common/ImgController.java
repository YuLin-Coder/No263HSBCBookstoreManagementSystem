package com.beauty.controller.common;


import cn.hutool.core.util.ObjectUtil;
import com.beauty.config.Result;
import com.beauty.config.contants.AppConstants;
import com.beauty.config.exception.RRException;
import com.beauty.config.properties.FileProperties;
import com.beauty.config.utile.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 文件上传
 */
@Controller
@RequestMapping("file")
public class ImgController {

    @Autowired
    private FileProperties properties;


    /**
     * 文件上传返回code为200
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result uplaod(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, Object> map = new LinkedHashMap<>();
        FileUtil.checkSize(properties.getMaxSize(), file.getSize());
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        File files = FileUtil.upload(file, properties.getPath().getPath() + File.separator);
        if (ObjectUtil.isNull(files)) {
            throw new RRException("上传失败");
        }

        String url = AppConstants.HOME_URL + "files/" + files.getName();

        return Result.success(url, "文件上传成功");
    }


}
