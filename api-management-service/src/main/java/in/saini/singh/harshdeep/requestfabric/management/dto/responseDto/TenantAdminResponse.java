package in.saini.singh.harshdeep.requestfabric.management.dto.responseDto;

import in.saini.singh.harshdeep.requestfabric.management.entity.ManagementUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TenantAdminResponse {
    private Long id;
    private Long organizationId;
    private String username;
    private boolean enabled;

    public static TenantAdminResponse from(ManagementUser user) {
        return new TenantAdminResponse(user.getId(), user.getOrganization().getId(), user.getUsername(),
                user.isEnabled());
    }
}
