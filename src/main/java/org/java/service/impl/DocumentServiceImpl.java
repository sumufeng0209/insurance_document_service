package org.java.service.impl;

import org.java.dao.DocumentMapper;
import org.java.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;
    @Override
    public Map<String,Object> findByConditions(Map<String, Object> map) {
        int pageIndex = 1;
        int pageSize = 8;
        if (map.get("pageIndex") != null && map.get("pageIndex") != ""){
            pageIndex = Integer.parseInt(map.get("pageIndex").toString());
        }
        if (map.get("pageSize") != null && map.get("pageSize") != ""){
            pageSize = Integer.parseInt(map.get("pageSize").toString());
        }
        map.put("pageIndex",(pageIndex - 1) * pageSize);
        map.put("pageSize",pageSize);

        Map<String,Object> dataMap = new HashMap<>();
        List<Map<String,Object>> data = documentMapper.findByConditions(map);
        dataMap.put("code",0);
        dataMap.put("msg","");
        dataMap.put("pageIndex",pageIndex);
        int count = documentMapper.findCountByConditions(map);
        int pageCount = count % pageSize == 0?count / pageSize:(count / pageSize) + 1;
        dataMap.put("pageCount",pageCount);
        dataMap.put("count",count);
        dataMap.put("data",data);
        return dataMap;
    }

    @Override
    public Integer findCountByConditions(Map<String, Object> map) {
        return documentMapper.findCountByConditions(map);
    }

    @Override
    public void add(Map<String, Object> map) {
        documentMapper.add(map);
    }

    @Override
    public void update(Map<String, Object> map) {
        System.out.println(map);
        documentMapper.update(map);
    }

    @Override
    public void delete(String documents_id) {
        documentMapper.delete(documents_id);
    }
}
