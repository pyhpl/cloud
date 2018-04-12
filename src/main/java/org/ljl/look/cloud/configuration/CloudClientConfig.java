package org.ljl.look.cloud.configuration;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudClientConfig {
    @Bean
    public COSClient COSClient(
            @Value("${cloud.secretId}") String secretId,
            @Value("${cloud.secretKey}") String secretKey,
            @Value("${cloud.region}") String region
    ) {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig =  new ClientConfig(new Region(region));
        return new COSClient(cred, clientConfig);
    }
}
