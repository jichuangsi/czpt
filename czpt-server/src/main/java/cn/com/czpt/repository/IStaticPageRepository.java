package cn.com.czpt.repository;

import cn.com.czpt.entity.StaticPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaticPageRepository extends JpaRepository<StaticPage,Integer> {
}
