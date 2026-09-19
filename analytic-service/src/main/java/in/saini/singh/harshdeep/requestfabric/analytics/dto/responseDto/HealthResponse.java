package in.saini.singh.harshdeep.requestfabric.analytics.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealthResponse {

    private String serviceName;
    private String serviceDescription;
    private String serviceStatus;
}
