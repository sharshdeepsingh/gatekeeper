package in.saini.singh.harshdeep.requestfabric.management.service;

import in.saini.singh.harshdeep.requestfabric.management.dto.requestDto.LoginRequest;
import in.saini.singh.harshdeep.requestfabric.management.dto.responseDto.LoginResponse;
import in.saini.singh.harshdeep.requestfabric.management.entity.ManagementUser;
import in.saini.singh.harshdeep.requestfabric.management.entity.OrganizationStatus;
import in.saini.singh.harshdeep.requestfabric.management.exception.ApiException;
import in.saini.singh.harshdeep.requestfabric.management.repository.ManagementUserRepository;
import org.springframework.transaction.annotation.Transactional;import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthenticationService {

    private ManagementUserRepository managementUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final Long expirationInSeconds;


    public AuthenticationService(ManagementUserRepository managementUserRepository, PasswordEncoder passwordEncoder,
                                 JwtEncoder jwtEncoder,@Value("${security.jwt.expiration-seconds}") Long expirationInSeconds){
        this.managementUserRepository=managementUserRepository;
        this.expirationInSeconds=expirationInSeconds;
        this.jwtEncoder=jwtEncoder;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional(readOnly = true)
    public LoginResponse login(@Valid LoginRequest request) {
        ManagementUser user= managementUserRepository.findByUsername(request.getUsername())
                .orElseThrow(()->invalidCredentials());

        if(!user.isEnabled() || !passwordEncoder.matches(request.getPassword(),user.getPasswordHash())){
            throw invalidCredentials();
        }

        if(user.getOrganization()!=null && user.getOrganization().getStatus() != OrganizationStatus.ACTIVE){
            throw  new ApiException(HttpStatus.FORBIDDEN,"The organization is not active");
        }

        Instant issuedAt= Instant.now();
        String role=user.getRole().getName();
        Long organizationId= user.getOrganization()== null ? null: user.getOrganization().getId();

        JwtClaimsSet.Builder claims=JwtClaimsSet.builder()
                .issuer("gatekeeper-api-management")
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(expirationInSeconds))
                .subject(user.getUsername())
                .claim("role",role);

        if(organizationId!=null){
            claims.claim("organizationId",organizationId);
        }

        JwsHeader header= JwsHeader.with(MacAlgorithm.HS256).build();

        String token= jwtEncoder.encode(
                JwtEncoderParameters.from(header,claims.build())).getTokenValue();

                return new LoginResponse(token,"Bearer",expirationInSeconds,role,organizationId);

    }

    private  RuntimeException invalidCredentials(){
        return new ApiException(HttpStatus.UNAUTHORIZED,"Invalid Username or password");
    }
}
