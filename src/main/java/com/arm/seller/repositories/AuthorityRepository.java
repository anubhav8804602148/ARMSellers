package com.arm.seller.repositories;

import com.arm.seller.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("select a from Authority a where a.authority=?1")
    public Authority findAuthorityByName(String name);
}
