package miu.videokabbee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Subscription {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private String paymentStatus;
    private String name;
    private Double price;



    /**
     * Checks if the subscription is currently active, meaning that the current date is between the start and end dates.
     *
     * @return true if the subscription is active, false otherwise
     */
    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return now.isAfter(startDate) && now.isBefore(endDate) && isActive;
    }

    /**
     * Calculates the remaining days of the subscription based on the current date and the end date.
     *
     * @return the number of days remaining in the subscription
     */
    public int getRemainingDays() {
        LocalDate now = LocalDate.now();
        return (int) now.until(endDate).getDays();
    }
}

