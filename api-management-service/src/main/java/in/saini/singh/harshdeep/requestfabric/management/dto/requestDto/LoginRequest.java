package in.saini.singh.harshdeep.requestfabric.management.dto.requestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Valid
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {


    @NotNull
    private String username;

    @NotNull
    private String password;
}
