package cn.com.czpt.service;

import cn.com.czpt.commons.Md5Util;
import cn.com.czpt.constant.ResultCode;
import cn.com.czpt.entity.BackUser;
import cn.com.czpt.exception.BackUserException;
import cn.com.czpt.model.BackUserLoginModel;
import cn.com.czpt.model.UserInfoForToken;
import cn.com.czpt.repository.IBackUserRepository;
import cn.com.czpt.util.MappingEntity2ModelCoverter;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class BackUserService {

    @Resource
    private IBackUserRepository backUserRepository;
    @Resource
    private BackTokenService backTokenService;

    public void registBackUser(BackUser backUser)throws BackUserException{
        if (StringUtils.isEmpty(backUser.getAccount()) || StringUtils.isEmpty(backUser.getPwd())
                || StringUtils.isEmpty(backUser.getUserName()) || StringUtils.isEmpty(backUser.getRoleId())){
            throw new BackUserException(ResultCode.PARAM_MISS_MSG);
        }
        if (backUserRepository.countByaccount(backUser.getAccount())>0){
            throw new BackUserException(ResultCode.ACCOUNT_ISEXIST_MSG);
        }
        backUser.setStatus("A");
        backUser.setPwd(Md5Util.encodeByMd5(backUser.getPwd()));
        backUserRepository.save(backUser);
    }

    public String loginBackUser(BackUserLoginModel model)throws BackUserException{
        if (StringUtils.isEmpty(model.getAccount()) || StringUtils.isEmpty(model.getPwd())){
            throw new BackUserException(ResultCode.PARAM_MISS_MSG);
        }
        BackUser backUser=backUserRepository.findByAccountAndPwd(model.getAccount(),Md5Util.encodeByMd5(model.getPwd()));
        if (backUser==null){
            throw new BackUserException(ResultCode.ACCOUNT_NOTEXIST_MSG);
        }
        String user=JSONObject.toJSONString(MappingEntity2ModelCoverter.CONVERTERFROMBACKUSERINFO(backUser));
        try {
            return backTokenService.createdToken(user);
        }catch (UnsupportedEncodingException e){
            throw new BackUserException(e.getMessage());
        }
    }

    public void insertSuperMan() throws BackUserException {
        if (backUserRepository.countByaccount("admin") > 0){
            throw new BackUserException(ResultCode.ACCOUNT_ISEXIST_MSG);
        }
        BackUser userInfo = new BackUser();
        userInfo.setStatus("A");
        userInfo.setAccount("admin");
        userInfo.setUserName("admin");
        userInfo.setRoleId("123456");
        userInfo.setRoleName("M");
        userInfo.setDeptId("123456");
        userInfo.setDeptName("S");
        userInfo.setPwd(Md5Util.encodeByMd5("admin"));
        userInfo.setCreatedTime(new Date().getTime());
        backUserRepository.save(userInfo);
    }

    public BackUser getBackUserById(UserInfoForToken userInfoForToken)throws BackUserException{
        BackUser user=backUserRepository.findByid(userInfoForToken.getUserId());
        if(user==null){
            throw new BackUserException(ResultCode.ACCOUNT_NOTEXIST_MSG);
        }
        return user;
    }
}
