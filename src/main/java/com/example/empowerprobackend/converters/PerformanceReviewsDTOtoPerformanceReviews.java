package com.example.empowerprobackend.converters;

import com.example.empowerprobackend.dto.PerformanceReviewsDTO;
import com.example.empowerprobackend.models.PerformanceReviewCycles;
import com.example.empowerprobackend.models.PerformanceReviews;
import com.example.empowerprobackend.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceReviewsDTOtoPerformanceReviews implements Converter<PerformanceReviewsDTO, PerformanceReviews> {
    @Override
    public PerformanceReviews convert(PerformanceReviewsDTO source) {
       if (source!=null){
           PerformanceReviews performanceReviews=new PerformanceReviews();
           performanceReviews.setRating(source.getRating());
           performanceReviews.setId(source.getId());
           performanceReviews.setFeedback(source.getFeedback());
           performanceReviews.setReviewDate(source.getReviewDate());
           User user=new User();
           user.setId(source.getUserId());
           performanceReviews.setUser(user);
           PerformanceReviewCycles performanceReviewCycles=new PerformanceReviewCycles();
           performanceReviewCycles.setId(source.getPerformanceReviewCyclesId());
           performanceReviews.setPerformanceReviewCycles(performanceReviewCycles);
           return performanceReviews;


       }
        return null;
    }
}
