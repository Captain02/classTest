package com.jzysoft.commonmoudle.lib.util.photo;

import com.jzysoft.common.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/upload")
public class Upload {
    private final ResourceLoader resourceLoader;
private String path = "D:/upload/";
    @Autowired
    public Upload(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    private String prefix = "radarsystem/basemanage/rsBhBhdetailinfo";


    @RequestMapping(value = "")
    public String RsBhBhdetailinfo() {
        return prefix + "/tests";
    }
    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @PostMapping("/fileUpload")
    public String upload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){

        // 要上传的目标文件存放路径
        String localPath = "D:/upload";
        // 上传成功或者失败的提示
        String msg = "";

        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            msg = "上传成功！";
        }else {
            msg = "上传失败！";

        }
//        获得文件名
        String fileName=file.getOriginalFilename();
//        获得后缀名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);


        // 显示图片
        map.put("msg", msg);
        map.put("fileName", file.getOriginalFilename());
        return prefix + "/tests";
    }
    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("/show")
    public ResponseEntity showPhotos(String fileName){

        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
