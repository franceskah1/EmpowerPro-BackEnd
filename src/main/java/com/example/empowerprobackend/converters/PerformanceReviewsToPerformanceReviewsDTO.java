package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.PerformanceReviewsDTO;
import com.example.empowerprobackend.models.PerformanceReviews;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceReviewsToPerformanceReviewsDTO implements Converter<PerformanceReviews, PerformanceReviewsDTO> {
    @Override
    public PerformanceReviewsDTO convert(PerformanceReviews source) {
       if (source!=null){
           PerformanceReviewsDTO performanceReviewsDTO=new PerformanceReviewsDTO();
           performanceReviewsDTO.setId(source.getId());
           performanceReviewsDTO.setReviewDate(source.getReviewDate());
           performanceReviewsDTO.setFeedback(source.getFeedback());
           performanceReviewsDTO.setRating(source.getRating());
           performanceReviewsDTO.setUserId(source.getUser().getId());
           performanceReviewsDTO.setPerformanceReviewCyclesId(source.getPerformanceReviewCycles().getId());
           return performanceReviewsDTO;
       }
        return null;
    }
}
