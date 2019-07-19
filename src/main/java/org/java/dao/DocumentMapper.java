package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DocumentMapper {
    List<Map<String,Object>> findByConditions(Map<String,Object> map);
    Integer findCountByConditions(Map<String,Object> map);
    void update(Map<String,Object> map);
    void add(Map<String,Object> map);
    void delete(@Param("documents_id") String documents_id);
}
