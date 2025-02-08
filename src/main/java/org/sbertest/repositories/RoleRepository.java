package org.sbertest.repositories;

import org.sbertest.entuty.RoleUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleUsers, Long> {
}
