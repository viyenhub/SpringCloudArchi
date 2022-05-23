package org.example.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.example.entity.CommonResult;

public class CustomerExceptionHandler {

    public static CommonResult  myHandler01(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 全局服务不可用 01");
    }

    public static CommonResult  myHandler02(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 全局服务不可用 02");
    }
}
