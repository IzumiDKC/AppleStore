package Nhom08_AppleStore.repository;

import Nhom08_AppleStore.model.YearManufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface YearManufactureRepository extends JpaRepository<YearManufacture, Long> {

}
