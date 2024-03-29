package cn.com.czpt.repository;

import cn.com.czpt.entity.UrlRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IUrlRelationRepository extends JpaRepository<UrlRelation,Integer> {
    List<UrlRelation> findByRoleId(String roleId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM urlrelation WHERE role_id = ?1",nativeQuery = true)
    void deleteForRoleId(String roleId);
}
