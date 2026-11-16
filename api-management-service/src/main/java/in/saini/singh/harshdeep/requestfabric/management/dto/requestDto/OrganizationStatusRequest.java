package in.saini.singh.harshdeep.requestfabric.management.dto.requestDto;

import in.saini.singh.harshdeep.requestfabric.management.entity.OrganizationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationStatusRequest {

    @NotNull
    private  OrganizationStatus status;
}
