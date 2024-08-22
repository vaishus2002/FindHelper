package com.app.repository;

import com.app.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("select c from CategoryEntity c where id=:cid")
    CategoryEntity giveEntity(Long cid);
    
}
