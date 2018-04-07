package cloud.mapper;

import cloud.entity.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImageMapper {
    @Insert("INSERT INTO image VALUES(#{uuid}::uuid, #{url}, #{belongTo}::uuid, #{valid})")
    void insert(Image image);

    @Delete("DELETE FROM image WHERE belong_to = #{belongTo}::uuid")
    void delete(@Param("belongTo") String belongTo);

    @Select("SELECT * FROM image WHERE belong_to = #{belongTo}::uuid AND valid = 1")
    List<Image> select(@Param("belongTo") String belongTo);
}
