package cn.com.czpt.repository;

import cn.com.czpt.entity.BackUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBackUserRepository extends JpaRepository<BackUser,String> {
    int countByaccount(String account);
    BackUser findByAccountAndPwd(String account,String pwd);
    BackUser findByid(String id);
}
