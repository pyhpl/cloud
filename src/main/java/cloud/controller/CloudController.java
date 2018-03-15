package cloud.controller;

import cloud.service.CloudService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/cloud")
public class CloudController {

    @Autowired
    private CloudService cloudService;

    @PutMapping("/{bucketName}/{objectName:.+}")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders putObject(@PathVariable String bucketName,
                                 @PathVariable String objectName,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        String url = cloudService.createObject(bucketName, objectName, file);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        return headers;
    }

    @DeleteMapping("/{bucketName}/{objectName:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteObject(@PathVariable("bucketName") String bucketName,
                             @PathVariable("objectName") String objectName) {
        cloudService.deleteObject(bucketName, objectName);
    }

}
