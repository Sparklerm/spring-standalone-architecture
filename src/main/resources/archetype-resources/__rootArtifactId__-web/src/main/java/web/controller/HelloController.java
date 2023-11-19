package ${groupId}.web.controller;

import ${groupId}.service.hello.service.IHelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * DemoController
 */
@RestController
@RequestMapping("/demo")
@Api(tags = "示例Controller")
public class HelloController {

    @Resource
    private IHelloService helloService;

    @GetMapping("/hello")
    @ApiOperation(value = "示例接口")
    public String hello() {
        return helloService.hello();
    }
}
