package com.example.empowerprobackend.models;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceReviews extends BaseEntity {

    private LocalDate reviewDate;
    private int rating;
    private String feedback;

    @ManyToOne
    @JoinColumn(name="performanceReviewCycles_Id",referencedColumnName = "id")
    PerformanceReviewCycles performanceReviewCycles;

    @ManyToOne
    @JoinColumn(name="user_Id",referencedColumnName = "id")
    private User user;

    //many performance reviews can be associated with one performance review cycle
    //many performance reviews can be associated with one employee.
}
