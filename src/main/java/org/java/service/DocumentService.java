package org.java.service;


import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface DocumentService {
    Map<String,Object> findByConditions(Map<String,Object> map);
    Integer findCountByConditions(Map<String,Object> map);
    void update(Map<String,Object> map);
    void add(Map<String,Object> map);
    void delete(@Param("documents_id") String documents_id);
}
