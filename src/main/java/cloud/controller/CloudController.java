package cloud.controller;

import cloud.service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/cloud")
public class CloudController {

    @Autowired
    private CloudService cloudService;

    @PostMapping("/{bucketName}/{objectName:.+}")
    @ResponseStatus(HttpStatus.CREATED)
    public String putObject(@PathVariable String bucketName,
                                 @PathVariable String objectName,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        return cloudService.createObject(bucketName, objectName, file);
    }

    @DeleteMapping("/{bucketName}/{objectName:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteObject(@PathVariable("bucketName") String bucketName,
                             @PathVariable("objectName") String objectName) {
        cloudService.deleteObject(bucketName, objectName);
    }

}
