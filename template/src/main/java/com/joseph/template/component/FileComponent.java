package com.joseph.template.component;

import com.joseph.framework.exception.ApplicationException;
import com.joseph.framework.exception.ErrorResponseMsgEnum;
import com.joseph.framework.result.Result;
import com.joseph.framework.utils.stomp.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件组件
 *
* @author Joseph
* @since 2020-06-20 19:38
*/
@Component
public class FileComponent {

    // 路径硬编码，不是很好，真实生产环境中应该是用CDN云服务
    @Value("${basePath4Photo}")
    private String basePath4Photo;

    @Value("${server.port}")
    private String serverPort;


    public String uploadPicture(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        if (!photo(originalFilename)) {
            throw new ApplicationException(ErrorResponseMsgEnum.INVALID_PARAM);
        }

        // 按日期分组文件夹，涉及本地操作系统权限原因，要提前创建好文件夹
        File directory = new File(basePath4Photo);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new RuntimeException("create dir error!");
            }
        }

        String suffix = photoSuffix(originalFilename);
        String fileName = CommonUtil.uuid();
        fileName += "." + suffix;

        String basePath = directory.getAbsolutePath();
        File file = new File(basePath + File.separator + fileName);
        multipartFile.transferTo(file);

        // 获取服务器ip，本地开发环境 或 云服务生产环境
        String ip = getIP();
        return  "http://" + ip + basePath4Photo + File.separator + fileName;
    }


    private boolean photo(String originalFilename) {
        if (CommonUtil.isBlank(originalFilename)) {
            return false;
        }
        String suffix = photoSuffix(originalFilename);
        if (!suffix.equalsIgnoreCase("jpg") &&
            !suffix.equalsIgnoreCase("png")) {
            return false;
        }
        return true;
    }

    private String getIP() {
        return CommonUtil.getServerIp() + ":" + serverPort;
    }

    private String photoSuffix(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
    }


}
