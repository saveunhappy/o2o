package com.imooc.o2o.controller.superadmin;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/superadmin")
@Slf4j
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping("/listarea")
    @ResponseBody
    private Map<String, Object> listArea() {
        log.info("===start===");
        long startTime = System.currentTimeMillis();
        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<Area> list = areaService.getAreaList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            throw new RuntimeException(e);
        }
        log.error("test error!");
        long endTime = System.currentTimeMillis();
        log.debug("costTime[{},ms]", endTime - startTime);
        log.info("===end===");
        return modelMap;
    }
}
