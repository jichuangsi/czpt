package cn.com.czpt.repository;

import cn.com.czpt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepostioy extends JpaRepository<Role,String> {
}
