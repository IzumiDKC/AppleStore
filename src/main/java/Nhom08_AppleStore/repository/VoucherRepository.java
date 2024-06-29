package Nhom08_AppleStore.repository;

import Nhom08_AppleStore.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);
}
