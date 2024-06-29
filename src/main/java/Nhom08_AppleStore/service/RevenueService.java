package Nhom08_AppleStore.service;

import Nhom08_AppleStore.model.Revenue;
import Nhom08_AppleStore.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class RevenueService {
    @Autowired
    private RevenueRepository revenueRepository;

    public Revenue getRevenueByDate(LocalDate date){
        return revenueRepository.findByDate(date)
                .orElse(new Revenue(date, 0.0, 0));
    }

    public List<Revenue> getRevenuesByMonth(YearMonth month) {
        LocalDate startOfMonth = month.atDay(1);
        LocalDate endOfMonth = month.atEndOfMonth();
        return revenueRepository.findAllByDateBetween(startOfMonth, endOfMonth);
    }
}
