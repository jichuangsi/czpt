package cn.com.czpt.controller;

import cn.com.czpt.entity.BackUser;
import cn.com.czpt.exception.BackUserException;
import cn.com.czpt.model.BackUserLoginModel;
import cn.com.czpt.model.ResponseModel;
import cn.com.czpt.model.UserInfoForToken;
import cn.com.czpt.service.BackUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("后台用户相关功能")
@RestController
@RequestMapping("/backuser")
@CrossOrigin
public class BackUserController {
    @Resource
    private BackUserService backUserService;

    @ApiOperation("后台用户注册")
    @ApiImplicitParams({})
    @PostMapping("/registBackUser")
    public ResponseModel registBackUser(@RequestBody BackUser backUser){
        try {
            backUserService.registBackUser(backUser);
        }catch (BackUserException e){
            return ResponseModel.fail("",e.getMessage());
        }
        return ResponseModel.sucessWithEmptyData("");
    }

    @ApiOperation("后台用户登录")
    @ApiImplicitParams({})
    @PostMapping("/loginBackUser")
    public ResponseModel loginBackUser(@RequestBody BackUserLoginModel model){
        try {
            return ResponseModel.sucess("",backUserService.loginBackUser(model));
        }catch (BackUserException e){
            return ResponseModel.fail("",e.getMessage());
        }
    }

    @ApiOperation("获取登录用户信息")
    @ApiImplicitParams({})
    @PostMapping("/getLoginBackUserInfo")
    public ResponseModel getLoginBackUserInfo(@ModelAttribute UserInfoForToken userInfoForToken){
        try {
            return ResponseModel.sucess("",backUserService.getBackUserById(userInfoForToken));
        }catch (BackUserException e){
            return ResponseModel.fail("",e.getMessage());
        }
    }
}
