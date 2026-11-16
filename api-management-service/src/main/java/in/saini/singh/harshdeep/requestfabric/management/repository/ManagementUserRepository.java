package in.saini.singh.harshdeep.requestfabric.management.repository;

import in.saini.singh.harshdeep.requestfabric.management.entity.ManagementUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagementUserRepository extends JpaRepository<ManagementUser,Long> {


    Optional<ManagementUser> findByUsername( String username);

    Optional<ManagementUser> findByOrganizationId(Long organizationId);

    boolean existsByUsername(@NotBlank @Size(max = 100) String username);
}
