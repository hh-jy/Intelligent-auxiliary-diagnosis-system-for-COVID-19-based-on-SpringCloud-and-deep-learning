package com.jalynn.clientfeign;

import com.jalynn.common.LoginOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "user-login")
@FeignClient(name = "user-login")
public interface LoginClient {
    @GetMapping("/user/getAllForManage")
    public List<LoginOutput> getAll();
}
