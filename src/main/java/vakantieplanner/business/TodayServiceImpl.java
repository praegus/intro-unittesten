package vakantieplanner.business;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TodayServiceImpl implements TodayService {
    @Override
    public LocalDate getToday() {
        return LocalDate.now();
    }
}
