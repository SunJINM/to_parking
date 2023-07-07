package sun.project.to_parking.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @description 文件上传业务处理层
 */
@Service
public interface UploadFileService {
    /**
     * 实现单文件上传
     * @param multipartFile 接收文件对象
     * @return
     */
    Map<String,Object> uploadSingleFile(MultipartFile multipartFile);
    /**
     * 实现多文件上传
     * @param multipartFiles 接收文件对象数组
     */
    Map<String,Object> uploadMultipleFile(MultipartFile[] multipartFiles);
}
