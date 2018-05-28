package com.dubion.repository;

import com.dubion.domain.User;
import com.dubion.domain.UserExt;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Optional;


/**
 * Spring Data JPA repository for the UserExt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtRepository extends JpaRepository<UserExt, Long> {

    Optional<UserExt> findById(Long id);

    Optional<UserExt> findByUser(User user);
}
