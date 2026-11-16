package in.saini.singh.harshdeep.requestfabric.management.repository;

import in.saini.singh.harshdeep.requestfabric.management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String tenantAdmin);
}
