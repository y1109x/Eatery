package com.eatery.cd.mapper;

import com.eatery.cd.pojo.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface NoticeMapper {
    /**
     * 查找所有公告
     * @return  List<Notice>公告集合
     */
    List<Notice> findAll();

    void deleteNotices(String[] noticeIds);

    void addNotice(Notice notice);

    void updateState(@Param("state") int state, @Param("noticeIds") String[] noticeIds);

    void updateNotice(Notice notice);
}
