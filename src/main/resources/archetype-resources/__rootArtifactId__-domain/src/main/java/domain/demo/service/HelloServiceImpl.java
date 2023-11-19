package ${groupId}.domain.demo.service;

import ${groupId}.service.hello.service.IHelloService;
import org.springframework.stereotype.Service;

/**
 * @author MENGJIAO
 * @createDate 2023-11-19 0019 下午 01:00
 */
@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello() {
        return "Hello World!";
    }
}
