package net.xdclass.xdvideo.mapper;

import net.xdclass.xdvideo.domain.Video;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * video数据访问层
 */
public interface VideoMapper {


    @Select("select * from video")
    List<Video> findAll();

}
