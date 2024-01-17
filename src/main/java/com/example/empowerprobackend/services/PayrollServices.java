package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.PayrollDTOtoPayroll;
import com.example.empowerprobackend.converters.PayrollToPayrollDTO;
import com.example.empowerprobackend.dto.PayrollDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.repositories.PayrollRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PayrollServices {
    private final PayrollRepository payrollRepository;
    private final PayrollDTOtoPayroll payrollDTOtoPayroll;
    private final PayrollToPayrollDTO toPayrollDTO;

  //  private static final Logger logger = (Logger) LogManager.getLogger(PayrollServices.class);

    public List<PayrollDTO> findAll(){
        return payrollRepository.findAll().stream().map(payroll -> toPayrollDTO.convert(payroll)).collect(Collectors.toList());
    }
    public PayrollDTO getPayrollById(String id){
        Long parseId;
        try {
            parseId=Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Payroll id: \"" + id + "\" can't be parsed!");
        }
        return toPayrollDTO.convert(payrollRepository.findById(parseId).orElseThrow(() -> new NotFoundException("Record with id: " + id + " not found!")));
    }

    public String saveOrUpdatePayroll(PayrollDTO payrollDTO) {
        try {

          payrollRepository.save(payrollDTOtoPayroll.convert(payrollDTO));
            return "Payroll  Saved Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("The new payroll  could not be saved due to data access problems");
        } catch (Exception e) {
         //   logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }

    public String deletePayrollById(String id) {
        try {
            Long parseId = Long.parseLong(id);
            payrollRepository.deleteById(parseId);
            return "Record deleted successfully";
        } catch (NumberFormatException e) {

            throw new InternalError("Payroll id: \"" +id + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new InternalError("Payroll with id " + id + " not found");
        } catch (Exception e) {
         //   logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
    }


}
