package com.example.empowerprobackend.converters;

import com.example.empowerprobackend.dto.PerformanceReviewCyclesDTO;
import com.example.empowerprobackend.models.PerformanceReviewCycles;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceReviewCyclesDTOtoPerformanceReviewCycles implements Converter<PerformanceReviewCyclesDTO, PerformanceReviewCycles> {
    @Override
    public PerformanceReviewCycles convert(PerformanceReviewCyclesDTO source) {
        if (source!=null){
            PerformanceReviewCycles performanceReviewCycles=new PerformanceReviewCycles();
            performanceReviewCycles.setId(source.getId());
            performanceReviewCycles.setCycleName(source.getCycleName());
            performanceReviewCycles.setCycleStartDate(source.getCycleStartDate());
            performanceReviewCycles.setCycleEndDate(source.getCycleEndDate());
            return performanceReviewCycles;

        }
        return null;
    }
}
