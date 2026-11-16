package in.saini.singh.harshdeep.requestfabric.management.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class HealthResponse {

    private String serviceStatus;
    private String dbStatus;
    private LocalDateTime timeStamp;
}
