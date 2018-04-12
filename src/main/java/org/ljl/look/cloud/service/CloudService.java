package org.ljl.look.cloud.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CloudService {

    @Autowired
    private COSClient cloudClient;

    @Value("${cloud.appId}")
    private String appId;
    @Value("${cloud.region}")
    private String region;

    /** 创建对象 */
    public String createObject(String bucketName, String objectName, MultipartFile file) throws IOException {
        bucketName += "-" + appId; // 设置特定bucket名
        String objectPath = "/" + objectName; // 设置object路径
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize()); // 设置对象size
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectPath, file.getInputStream(), metadata);
        cloudClient.putObject(putObjectRequest); // 上传
        return "https://" + bucketName + ".cos." + region + ".myqcloud.com" + objectPath;
    }

    /** 删除对象 */
    public void deleteObject(String bucketName, String objectName) {
        cloudClient.deleteObject(bucketName, "/" + objectName);
    }

}










