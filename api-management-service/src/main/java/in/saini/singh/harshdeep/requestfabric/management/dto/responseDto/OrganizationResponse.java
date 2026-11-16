package in.saini.singh.harshdeep.requestfabric.management.dto.responseDto;


import in.saini.singh.harshdeep.requestfabric.management.entity.Organization;
import in.saini.singh.harshdeep.requestfabric.management.entity.OrganizationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrganizationResponse {

private Long id;
private String name;
private String slug;
private OrganizationStatus status;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;

public static OrganizationResponse from(Organization organization){
    return new OrganizationResponse(
            organization.getId(),
            organization.getName(),
            organization.getSlug(),
            organization.getStatus(),
            organization.getCreatedAt()
            ,organization.getUpdatedAt()
    );
}
}
