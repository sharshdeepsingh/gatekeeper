package in.saini.singh.harshdeep.requestfabric.management.controller;

import in.saini.singh.harshdeep.requestfabric.management.dto.requestDto.OrganizationRequest;
import in.saini.singh.harshdeep.requestfabric.management.dto.requestDto.OrganizationStatusRequest;
import in.saini.singh.harshdeep.requestfabric.management.dto.requestDto.TenantAdminRequest;
import in.saini.singh.harshdeep.requestfabric.management.dto.responseDto.OrganizationResponse;
import in.saini.singh.harshdeep.requestfabric.management.dto.responseDto.TenantAdminResponse;
import in.saini.singh.harshdeep.requestfabric.management.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platform/organizations")
public class PlatformOrganizationController {

    private final OrganizationService organizationService;

    public PlatformOrganizationController(OrganizationService organizationService){
        this.organizationService=organizationService;
    }

    // CRUD operations

    // 1. Create an organization

    @PostMapping
    public ResponseEntity<OrganizationResponse>  create(
            @Valid @RequestBody OrganizationRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(organizationService.create(request));
    }

    // 2. Read All organization
    @GetMapping
    public ResponseEntity<List<OrganizationResponse>> findAll(){
        return ResponseEntity.ok(organizationService.findAll());
    }

    // 3. Read One
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.findById(id));
    }

    // 4. update
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponse> findById(@PathVariable Long id ,
                                                         @Valid @RequestBody OrganizationRequest request){
        return ResponseEntity.ok(organizationService.update(id,request));
    }

    // 5. patch
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrganizationResponse> changeStatus(@PathVariable Long id,
                                                             @Valid @RequestBody
                                                             OrganizationStatusRequest request){
        return ResponseEntity.ok(organizationService.changeStatus(id,request));
    }

    // 6. Delete(Soft Delete)
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteById(@PathVariable Long id){
        organizationService.delete(id);
    return ResponseEntity.noContent().build();
    }


    // CRUD Operations for tenant admins
    @PostMapping("/{organizationId}/tenant-admin")
    public ResponseEntity<TenantAdminResponse> createTenantAdmin(@PathVariable Long organizationId,
                                                                 @Valid @RequestBody TenantAdminRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(organizationService.createTenantAdmin(organizationId, request));
    }

    @GetMapping("/{organizationId}/tenant-admin")
    public ResponseEntity<TenantAdminResponse> getTenantAdmin(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.getTenantAdmin(organizationId));
    }

    @PutMapping("/{organizationId}/tenant-admin")
    public ResponseEntity<TenantAdminResponse> updateTenantAdmin(@PathVariable Long organizationId,
                                                                 @Valid @RequestBody TenantAdminRequest request) {
        return ResponseEntity.ok(organizationService.updateTenantAdmin(organizationId, request));
    }

    @DeleteMapping("/{organizationId}/tenant-admin")
    public ResponseEntity<Void> deleteTenantAdmin(@PathVariable Long organizationId) {
        organizationService.deleteTenantAdmin(organizationId);
        return ResponseEntity.noContent().build();
    }
}
