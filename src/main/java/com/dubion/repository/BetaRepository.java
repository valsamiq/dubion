package com.dubion.repository;

import com.dubion.domain.Beta;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Beta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BetaRepository extends JpaRepository<Beta, Long>, JpaSpecificationExecutor<Beta> {

}
