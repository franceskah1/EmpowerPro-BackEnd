package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.PerformanceReviewCyclesDTOtoPerformanceReviewCycles;
import com.example.empowerprobackend.converters.PerformanceReviewCyclesToPerformanceReviewCyclesDTO;
import com.example.empowerprobackend.dto.PerformanceReviewCyclesDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.repositories.PerformanceReviewCyclesRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class PerformanceReviewCyclesService {
    private final PerformanceReviewCyclesRepository performanceReviewCyclesRepository;
    private final PerformanceReviewCyclesDTOtoPerformanceReviewCycles performanceReviewCyclesDTOtoPerformanceReviewCycles;
    private final PerformanceReviewCyclesToPerformanceReviewCyclesDTO performanceReviewCyclesToPerformanceReviewCyclesDTO;
    private static final Logger logger = LogManager.getLogger(AttendanceService.class);



    public String saveOrUpdatePerformanceReviewCycles(PerformanceReviewCyclesDTO performanceReviewCyclesDTO) {
        try {
          performanceReviewCyclesRepository.save(performanceReviewCyclesDTOtoPerformanceReviewCycles.convert(performanceReviewCyclesDTO));
            return "PerformanceReviewCycles  Saved Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("The new PerformanceReviewCycles  could not be saved due to data access problems");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }
    public PerformanceReviewCyclesDTO findById(String id) {
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("PerformanceReviewCycles id: \"" + id + "\" can't be parsed!");
        }
        return performanceReviewCyclesToPerformanceReviewCyclesDTO.convert(performanceReviewCyclesRepository.findById(parseId).orElseThrow(() -> new NotFoundException("Record with id: " + id + " not found!")));
    }


    public String deletePerformanceReviewCyclesById(String id) {

        try {
            Long parseId = Long.parseLong(id);
            performanceReviewCyclesRepository.deleteById(parseId);
            return "Record deleted successfully";
        } catch (NumberFormatException e) {

            throw new InternalError("PerformanceReviewCycles id: \"" +id + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new InternalError("DPerformanceReviewCycles with id " + id + " not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }


    }
    public List<PerformanceReviewCyclesDTO> findAll(){
        return performanceReviewCyclesRepository.findAll().stream().map(performanceReviewCycles -> performanceReviewCyclesToPerformanceReviewCyclesDTO.convert(performanceReviewCycles)).collect(Collectors.toList());
    }


}


