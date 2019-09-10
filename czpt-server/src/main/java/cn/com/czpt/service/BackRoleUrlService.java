package cn.com.czpt.service;

import cn.com.czpt.dao.mapper.IBackUserMapper;
import cn.com.czpt.entity.*;
import cn.com.czpt.model.*;
import cn.com.czpt.repository.*;
import cn.com.czpt.util.MappingEntity2ModelCoverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackRoleUrlService {
    @Resource
    private IParentNodeRepository parentNodeRepository;
    @Resource
    private IStaticPageRepository staticPageRepository;
    @Resource
    private IUrlRelationRepository urlRelationRepository;
    @Resource
    private IBackUserMapper backUserMapper;
    @Resource
    private IRoleRepostioy roleRepostioy;
    @Resource
    private IBackUserRepository backUserRepository;

    //获取全部父节点
    public List<ParentNode> getAllParentNode(){
        return parentNodeRepository.findAll();
    }
    //获取全部页面
    public List<StaticPageModel> getAllStaticPage(){
        return MappingEntity2ModelCoverter.CONVERTERFROMStaticPage(staticPageRepository.findAll());
    }

    //批量添加角色可以访问的页面
    public void batchInsertUrlRelation(List<UrlRelation> relationList){
        urlRelationRepository.saveAll(relationList);
    }

    //批量删除角色可以访问的页面
    public void batchDeleteUrlRelation(List<UrlRelation> relationList){
        urlRelationRepository.deleteInBatch(relationList);
    }

    //批量添加角色可以访问的页面
    public void batchUpdateUrlRelation(UrlModel model){
        urlRelationRepository.deleteForRoleId(model.getRelationList().get(0).getRoleId());
        urlRelationRepository.saveAll(model.getRelationList());
    }

    public List<RoleUrlModel> getStaticpageByRoleId(UserInfoForToken userInfo){
        BackUser backUser=backUserRepository.findByid(userInfo.getUserId());
        //管理员
        if(backUser.getRoleId().equalsIgnoreCase("123456")){
            return backUserMapper.getAllPage();
        }
        return backUserMapper.getPageByRoleId(backUser.getRoleId());
    }

    public List<RoleUrlModel> getStaticpageByRoleId2(String roleId){
        return backUserMapper.getPageByRoleId(roleId);
    }

    public List<Role> getAllRole(){
        return roleRepostioy.findAll();
    }

    public boolean checkUrl(UserInfoForToken userInfo,String url){
        BackUser backUser=backUserRepository.findByid(userInfo.getUserId());
        //管理员
        if(backUser.getRoleId().equalsIgnoreCase("123456")){
            return  true;
        }
        List<RoleUrlModel> models=backUserMapper.getPageByRoleId(backUser.getRoleId());
        for (RoleUrlModel item:models) {
            if (url.equals(item.getPageUrl()) || url.startsWith(item.getPageUrl())){
                return  true;
            }
        }
        return false;
    }
}
