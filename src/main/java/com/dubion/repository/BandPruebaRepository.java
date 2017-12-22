package com.dubion.repository;

import com.dubion.domain.BandPrueba;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BandPrueba entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BandPruebaRepository extends JpaRepository<BandPrueba, Long>, JpaSpecificationExecutor<BandPrueba> {

}
