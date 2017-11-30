package com.dubion.repository;

import com.dubion.domain.Sex;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Sex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SexRepository extends JpaRepository<Sex, Long> {

}
