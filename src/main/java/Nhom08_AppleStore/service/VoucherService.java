package Nhom08_AppleStore.service;

import Nhom08_AppleStore.model.Voucher;
import Nhom08_AppleStore.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public Optional<Voucher> getVoucherById(Long id) {
        return voucherRepository.findById(id);
    }

    public Voucher getVoucherByCode(String code) {
        return voucherRepository.findByCode(code);
    }

    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    public void deleteVoucherById(Long id) {
        voucherRepository.deleteById(id);
    }
}
