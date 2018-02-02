package com.dubion.repository;

import com.dubion.domain.Gamma;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Gamma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GammaRepository extends JpaRepository<Gamma, Long>, JpaSpecificationExecutor<Gamma> {

}
