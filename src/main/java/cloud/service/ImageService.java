package cloud.service;

import cloud.entity.Image;
import cloud.mapper.ImageMapper;
import core.util.UuidTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageMapper imageMapper;

    public void add(Image image) {
        image.setUuid(UuidTool.getValue());
        image.setValid((short) 1);
        imageMapper.insert(image);
    }

    public List<String> select(String belongTo) {
        return imageMapper.select(belongTo).stream()
                .map(Image::getUrl).collect(Collectors.toList());
    }
}
