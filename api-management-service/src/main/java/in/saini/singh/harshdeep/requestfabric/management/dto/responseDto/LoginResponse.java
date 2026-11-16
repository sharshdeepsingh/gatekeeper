package in.saini.singh.harshdeep.requestfabric.management.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String tokenType;
    private long expirationInSeconds;
    private String role;
    private Long organizationId;
}
