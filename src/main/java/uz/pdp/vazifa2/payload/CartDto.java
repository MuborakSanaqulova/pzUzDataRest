package uz.pdp.vazifa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartDto {

    private Set<Integer> productId;

    private Integer userId;

}
