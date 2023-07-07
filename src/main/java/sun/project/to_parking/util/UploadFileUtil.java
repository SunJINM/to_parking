package sun.project.to_parking.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class UploadFileUtil {
    /**
     * 上传图片到云服务器
     * @param multipartFile
     * @return
     */
    public static String uploadFileToECS(MultipartFile multipartFile) {
        //构建日期文件
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyMMdd");
        String datePath=dateFormat.format(new Date());
        //获取到文件名
        String originName= multipartFile.getOriginalFilename();
        //文件名
        String fileName = UUID.randomUUID().toString();
        //后缀
        String suffix=originName.substring(originName.lastIndexOf("."));
        //新文件名
        String newName=datePath+fileName+suffix;
        //保存文件的绝对路径
        try {
            String path = "/www/tomcat/file/uploadfile";
            String url="http://121.43.101.84:8080/uploadfile/";
            File folder=new File(path);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            multipartFile.transferTo(new File(folder+"/"+newName));
            //图片路径
            String filePath = url+newName;
            return filePath;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 上传图片到项目路径下
     */
    public static String uploadFileToPS(MultipartFile multipartFile,HttpServletRequest req) {
        System.out.println(multipartFile.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        // 上传的文件将保存在项目运行目录下的 uploadFile 文件夹，
        String realPath = req.getSession().getServletContext().getRealPath("uploadFile");//工程目录创建
        // 并且在 uploadFile 文件夹中通过日期对上传的文件归类保存
        String format = sdf.format(new Date());
        //2022/02/25
        File folder = new File(realPath +"/"+ format);
//        File folder = new File("E:"+File.separator+"图片"+File.separator+"壁纸");
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = multipartFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."));
        try {
            // 文件保存//文件必须已经存在
            System.out.println(folder.getPath());
            multipartFile.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String filePath = req.getScheme() + "://" + req.getServerName()
                    + ":"+req.getServerPort()+ "/monitor/uploadFile/"+format + newName;
            //编写日志
            log.info("\n文件已上传："+filePath);

            return filePath;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
