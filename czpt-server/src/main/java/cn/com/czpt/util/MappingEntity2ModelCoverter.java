package cn.com.czpt.util;

import cn.com.czpt.entity.BackUser;
import cn.com.czpt.entity.StaticPage;
import cn.com.czpt.model.StaticPageModel;
import cn.com.czpt.model.UserInfoForToken;

import java.util.ArrayList;
import java.util.List;

public class MappingEntity2ModelCoverter {
    public final static UserInfoForToken CONVERTERFROMBACKUSERINFO(BackUser userInfo){
        UserInfoForToken userInfoForToken = new UserInfoForToken();
        userInfoForToken.setUserId(userInfo.getId());
        userInfoForToken.setUserName(userInfo.getUserName());
        userInfoForToken.setRoleId(userInfo.getRoleId());
        return userInfoForToken;
    }

    public final static List<StaticPageModel> CONVERTERFROMStaticPage(List<StaticPage> staticPages){
        List<StaticPageModel> modelList=new ArrayList<>();
        staticPages.forEach(model->{
            StaticPageModel model1=new StaticPageModel();
            model1.setPageId(model.getId());
            model1.setPageName(model.getPageName());
            model1.setPageUrl(model.getPageUrl());
            model1.setParentNode(model.getParentNode());
            modelList.add(model1);
        });
        return modelList;
    }
}
