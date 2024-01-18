package com.yangtao.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: kante_yang
 * @Date: 2024/1/12
 */
// @Component
@Slf4j
public class CustomExceptionHandler implements BlockExceptionHandler {

    // 自定义异常处理器
    @Override
    public void handle(HttpServletRequest request,
        HttpServletResponse response, BlockException e) throws Exception {

        response.setStatus(429);
        PrintWriter writer = response.getWriter();
        String msg = "Blocked by Sentinel - ";
        log.info("custome exception handler process!");

        if (e instanceof FlowException) {
            msg += "Flow Exception";
        } else if (e instanceof DegradeException) {
            msg += "Degrade Exception";
        } else if (e instanceof SystemBlockException) {
            msg += "System Block Exception";
        } else if (e instanceof ParamFlowException) {
            msg += "Param Flow Exception";
        } else if (e instanceof AuthorityException) {
            msg += "Authority Exception";
        }

        writer.print(msg);
        writer.flush();
        writer.close();
    }
}
