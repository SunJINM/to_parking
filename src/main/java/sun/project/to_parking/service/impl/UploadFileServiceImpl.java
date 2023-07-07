package sun.project.to_parking.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.project.to_parking.service.UploadFileService;
import sun.project.to_parking.util.UploadFileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("ossUploadFileService")
@Transactional(rollbackFor = Exception.class)
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    private  HttpServletRequest req;
    @Override
    public Map<String, Object> uploadSingleFile(MultipartFile multipartFile) {
        Map map = new HashMap<>();
        try {
            /*
            存到项目路径下
             */
//            String filepath = UploadFileUtils.uploadFileToPS(multipartFile,req);
            /*
            存到阿里云服务器上
             */
            String filepath = UploadFileUtil.uploadFileToECS(multipartFile);
            /*
            存到oss对象存储上
             */
//            String filepath = UploadFileUtils.uploadFileToOSS(multipartFile);
            map.put("filepath", filepath);
        } catch (StringIndexOutOfBoundsException e) {
            map.put("error", "没有找到文件");
        }
        return map;
    }

    @Override
    public Map<String, Object> uploadMultipleFile(MultipartFile[] multipartFiles) {
        List<String> fileList = new ArrayList<>();
        Map map=new HashMap<>();
        try {
            if (multipartFiles.length > 0) {
                for (MultipartFile multipartFile:multipartFiles) {
                   /*
            存到项目路径下
             */
//            String filepath = UploadFileUtils.uploadFileToPS(multipartFile,req);
            /*
            存到阿里云服务器上
             */
            String filepath = UploadFileUtil.uploadFileToECS(multipartFile);
            /*
            存到oss对象存储上
             */
//            String filepath = UploadFileUtils.uploadFileToOSS(multipartFile);
                    fileList.add(filepath);
                }
                map.put("filepath",fileList);
            }else {
                map.put("error","没有找到文件");
            }
        } catch (StringIndexOutOfBoundsException e) {
            map.put("error","没有找到文件");
        }
        return map;
    }
}
