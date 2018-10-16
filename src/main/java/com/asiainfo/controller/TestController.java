package com.asiainfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("test")
public class TestController {

    @GetMapping("index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("pup")
    public String getPub(){
        System.out.println();
        return "pup";
    }

    @PostMapping("upload")
    @ResponseBody
    public String upload(String file){
        if (file == null || "".equals(file)) // 图像数据为空
            return "no";
        System.out.println(file);
        file = file.replaceAll("data:image/png;base64,", "");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(file);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream("d:\\"+System.currentTimeMillis()+".png");
            out.write(b);
            out.flush();
            out.close();

            return "y";
        } catch (Exception e) {
            e.printStackTrace();
            return "n";
        }
    }
}
