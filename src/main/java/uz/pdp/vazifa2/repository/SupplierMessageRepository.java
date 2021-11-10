package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.SupplierMessage;

@Repository
public interface SupplierMessageRepository extends JpaRepository<SupplierMessage, Integer> {

}
