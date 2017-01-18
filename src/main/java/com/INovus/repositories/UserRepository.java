package com.INovus.repositories;

import com.INovus.enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :name")
    User findByName(@Param("name") String name);

    @Query("select case when count(u) > 0 THEN true ELSE false END from User u WHERE lower(u.username) = lower(:name)")
    boolean existsByName(@Param("name") String name);
}
