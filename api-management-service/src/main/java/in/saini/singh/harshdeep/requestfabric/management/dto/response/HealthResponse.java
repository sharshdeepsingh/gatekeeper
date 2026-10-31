package in.saini.singh.harshdeep.requestfabric.management.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HealthResponse {

    private String serviceName;
    private String serviceDescription;
    private String status;
}
