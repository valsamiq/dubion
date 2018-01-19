package com.dubion.repository;

import com.dubion.domain.Alpha;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Alpha entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlphaRepository extends JpaRepository<Alpha, Long>, JpaSpecificationExecutor<Alpha> {
    @Query("select distinct alpha from Alpha alpha left join fetch alpha.betas")
    List<Alpha> findAllWithEagerRelationships();

    @Query("select alpha from Alpha alpha left join fetch alpha.betas where alpha.id =:id")
    Alpha findOneWithEagerRelationships(@Param("id") Long id);

}
