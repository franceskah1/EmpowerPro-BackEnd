package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.PerformanceReviewsDTOtoPerformanceReviews;
import com.example.empowerprobackend.converters.PerformanceReviewsToPerformanceReviewsDTO;
import com.example.empowerprobackend.dto.PerformanceReviewsDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.repositories.PerformanceReviewsRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PerformanceReviewsService {
    private final PerformanceReviewsRepository performanceReviewsRepository;
    private final PerformanceReviewsToPerformanceReviewsDTO toPerformanceReviewsDTO;
    private final PerformanceReviewsDTOtoPerformanceReviews otoPerformanceReviews;
    private static final Logger logger = LogManager.getLogger(AttendanceService.class);



    public List<PerformanceReviewsDTO> findAll() {
        return performanceReviewsRepository.findAll().stream().map(toPerformanceReviewsDTO::convert).collect(Collectors.toList());
    }

    public PerformanceReviewsDTO findById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);

        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException("PerformanceReview id :" + id + " can't be parsed!");
        }
        return toPerformanceReviewsDTO.convert(performanceReviewsRepository.findById(parseId).orElseThrow(() -> new NotFoundException("Record with id: " + id + " not found!")));
    }


    public String  deleteById(String id){
      try {
        Long parseId = Long.parseLong(id);
       performanceReviewsRepository.deleteById(parseId);
        return "Record deleted successfully";
    } catch (NumberFormatException e) {
        throw new InternalError(" PerformanceReview  id: \"" + id + "\" can't be parsed!");
    } catch (
    EmptyResultDataAccessException e) {
        throw new InternalError("PerformanceReview with id " + id + " not found");
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
    }
}

    public String saveOrUpdatePerformanceReview(PerformanceReviewsDTO performanceReviewsDTO) {
        try {
            performanceReviewsRepository.save(Objects.requireNonNull(otoPerformanceReviews.convert(performanceReviewsDTO)));
            return "PerformanceReview  Saved Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("The new record  could not be saved due to data access problems");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }
}