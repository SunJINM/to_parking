package sun.project.to_parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.project.to_parking.enums.ResultCode;
import sun.project.to_parking.service.UploadFileService;
import sun.project.to_parking.comment.Result;

import java.util.Map;

@Api(tags = "文件上传控制类")
@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {
    @Autowired
    private UploadFileService uploadFileService;


    @ApiOperation(value = "上传单张照片的接口")
    @PostMapping("/uploadImage")
    public Result uploadImg(@RequestParam(value = "file")MultipartFile multipartFile){
        Map<String,Object> map=uploadFileService.uploadSingleFile(multipartFile);
        return Result.responseResult(ResultCode.SUCCESS,map);
    }

    /**
     * @param multipartFiles
     * @return
     */
    @ApiOperation(value = "上传多张照片的接口")
    @PostMapping("/uploadImages")
    public Result uploadImg(@RequestParam(value = "file")MultipartFile[] multipartFiles){
        Map<String,Object> map=uploadFileService.uploadMultipleFile(multipartFiles);
        return Result.responseResult(ResultCode.SUCCESS,map);
    }
}
