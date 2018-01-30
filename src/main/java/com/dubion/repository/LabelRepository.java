package com.dubion.repository;

import com.dubion.domain.Label;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Label entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LabelRepository extends JpaRepository<Label, Long>, JpaSpecificationExecutor<Label> {

    @Query("select distinct label from Label label ")
    List<Label> findAllWithEagerRelationships();

    @Query("select label from Label label  where label.id = :id")
    Label findOneWithEagerRelationships(@Param("id") Long id);

    //@Query("select label from Label label left join fetch label.band where label.name=:name")
    Label findByNameContaining(String artistName);
}
