package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.oracle.tools.packager.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SentinelController {

    @GetMapping("/testA")
    public String testA() {
        return "***********testA**********";
    }

    @GetMapping("/testB")
    public String testB() {
        return "***********testB**********";
    }

    @GetMapping("/testC")
    public String testC() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.info("RT测试");
        return "**********testC**********";
    }

    @GetMapping("/testhotkey")
    @SentinelResource(value="testhotkey",blockHandler = "deal_testhotkey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "*************test hot key *********";
    }

    public String deal_testhotkey(String p1, String p2, BlockException exception){
        return "*************handle test hot key exception*********";
    }
}
