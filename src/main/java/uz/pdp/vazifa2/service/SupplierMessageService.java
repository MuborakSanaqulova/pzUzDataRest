package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.SupplierMessage;
import uz.pdp.vazifa2.payload.SupplierMessageDto;
import uz.pdp.vazifa2.repository.SupplierMessageRepository;

import java.util.Optional;

@Service
public class SupplierMessageService {

    @Autowired
    SupplierMessageRepository supplierMessageRepository;

    public Page<SupplierMessage> getAll(Pageable pageable) {
        return supplierMessageRepository.findAll(pageable);
    }

    public SupplierMessage getOne(Integer id) {
        Optional<SupplierMessage> supplierMessage = supplierMessageRepository.findById(id);
        if (supplierMessage.isPresent())
            return supplierMessage.get();
        return null;
    }

    public ApiResponse addSupplierMessage(SupplierMessageDto supplierMessageDto) {
        SupplierMessage supplierMessage = new SupplierMessage();
        supplierMessage.setUserMessage(supplierMessageDto.getUserMessage());
        supplierMessage.setUserEmail(supplierMessageDto.getUserEmail());
        supplierMessage.setUserName(supplierMessageDto.getUserName());

        supplierMessageRepository.save(supplierMessage);

        return new ApiResponse("successfully saved", true);
    }

    public ApiResponse edit(Integer id, SupplierMessageDto supplierMessageDto) {
        SupplierMessage supplierMessage = new SupplierMessage();
        supplierMessage.setId(id);
        supplierMessage.setUserName(supplierMessageDto.getUserName());
        supplierMessage.setUserMessage(supplierMessageDto.getUserMessage());
        supplierMessage.setUserEmail(supplierMessageDto.getUserEmail());

        supplierMessageRepository.save(supplierMessage);

        return new ApiResponse("successfully edited", true);
    }

    public ApiResponse deleteSM(Integer id) {
        Optional<SupplierMessage> optionalSupplierMessage = supplierMessageRepository.findById(id);
        if (!optionalSupplierMessage.isPresent())
            return new ApiResponse("not found", false);

        supplierMessageRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }
}
