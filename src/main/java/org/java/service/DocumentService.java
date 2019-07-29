package org.java.service;


import org.apache.ibatis.annotations.Param;

import javax.servlet.ServletOutputStream;
import java.util.Map;

public interface DocumentService {
    Map<String,Object> findByConditions(Map<String,Object> map);
    Integer findCountByConditions(Map<String,Object> map);
    void update(Map<String,Object> map);
    void add(Map<String,Object> map);
    void delete(@Param("documents_id") String documents_id);
    Map<String,Object> showReportCase(String schedule_type_id);
    void addFile(Map<String,Object> map);
    Map<String,Object> showDocumentFile(String compensate_case_id);
    void showDocumentImage(String file_id, ServletOutputStream outputStream);
}
