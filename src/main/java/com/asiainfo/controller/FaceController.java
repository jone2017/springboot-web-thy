package com.asiainfo.controller;

import com.asiainfo.dao.FaceInfoRepository;
import com.asiainfo.domain.FaceInfo;
import com.asiainfo.dto.FaceDto;
import com.asiainfo.websocket.WebSocketServer;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class FaceController {

    @Autowired
    private FaceInfoRepository faceInfoRepository;

    @PostMapping("/facesearch")
    public ModelMap faceSearch(@RequestBody FaceDto faceDto){
        ModelMap modelMap = new ModelMap();
        modelMap.put("result",0);
        try {
            FaceInfo faceInfo = faceInfoRepository.getOne(faceDto.getId());
            if (faceInfo != null && faceInfo.getId() != null){
                Map map = new HashMap();
                map.put("age",faceInfo.getAge());
                map.put("sex",faceInfo.getSex());
                map.put("id",faceDto.getId());
                map.put("faceimage",faceDto.getFaceimage());
                JSONObject jsonObject = JSONObject.fromObject(map);
                WebSocketServer.sendInfo(jsonObject.toString());
                modelMap.put("result",1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelMap;
    }
}
