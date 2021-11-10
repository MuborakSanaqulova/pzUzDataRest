package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.SupplierMessage;
import uz.pdp.vazifa2.payload.SupplierMessageDto;
import uz.pdp.vazifa2.service.SupplierMessageService;


@RestController
@RequestMapping("api/supplierMessage")
public class SupplierMessageController {

    @Autowired
    SupplierMessageService supplierMessageService;

    @GetMapping
    public ResponseEntity<Page<SupplierMessage>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<SupplierMessage> all = supplierMessageService.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierMessage> getOne(@PathVariable Integer id) {
        SupplierMessage serviceOne = supplierMessageService.getOne(id);
        return ResponseEntity.ok(serviceOne);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addSupplierMessage(@RequestBody SupplierMessageDto supplierMessageDto) {
        ApiResponse apiResponse = supplierMessageService.addSupplierMessage(supplierMessageDto);

        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editSupplierMessage(@PathVariable Integer id, @RequestBody SupplierMessageDto supplierMessageDto) {
        ApiResponse edit = supplierMessageService.edit(id, supplierMessageDto);
        if (edit.isSuccess())
            return ResponseEntity.status(200).body(edit);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSupplierMessage(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierMessageService.deleteSM(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
}
