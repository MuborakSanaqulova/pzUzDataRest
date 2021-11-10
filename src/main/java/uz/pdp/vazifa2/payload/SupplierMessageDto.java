package uz.pdp.vazifa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierMessageDto {

    private String userName;

    private String userEmail;

    private String userMessage;

}
