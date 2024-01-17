package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.PerformanceReviewCyclesDTO;
import com.example.empowerprobackend.models.PerformanceReviewCycles;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceReviewCyclesToPerformanceReviewCyclesDTO implements Converter<PerformanceReviewCycles, PerformanceReviewCyclesDTO> {
    @Override
    public PerformanceReviewCyclesDTO convert(PerformanceReviewCycles source) {
        if (source!=null){
            PerformanceReviewCyclesDTO performanceReviewCyclesDTO=new PerformanceReviewCyclesDTO();
            performanceReviewCyclesDTO.setId(source.getId());
            performanceReviewCyclesDTO.setCycleName(source.getCycleName());
            performanceReviewCyclesDTO.setCycleEndDate(source.getCycleEndDate());
            performanceReviewCyclesDTO.setCycleStartDate(source.getCycleStartDate());
            return performanceReviewCyclesDTO;
        }
        return null;
    }
}
