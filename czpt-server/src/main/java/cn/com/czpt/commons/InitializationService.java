package cn.com.czpt.commons;

import cn.com.czpt.service.BackUserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class InitializationService {
    @Resource
    private BackUserService backUserService;

    @PostConstruct
    public void insertSuperMan(){
        try {
            backUserService.insertSuperMan();
        } catch (Exception e) {
        }
    }
}
