package in.saini.singh.harshdeep.requestfabric.management.repository;

import in.saini.singh.harshdeep.requestfabric.management.entity.Organization;
import in.saini.singh.harshdeep.requestfabric.management.entity.OrganizationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {


     Optional<Organization> findByIdAndStatusNot(Long id, OrganizationStatus organizationStatus);

    boolean existsByNameIgnoreCase(String name);

    boolean existsBySlugIgnoreCase(String slug);

    List<Organization> findAllByStatusNot(OrganizationStatus organizationStatus);
}
