package com.example.empowerprobackend.services;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.Role;
import com.example.empowerprobackend.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.rmi.UnexpectedException;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private static final Logger logger = LogManager.getLogger(RoleService.class);

    public List<Role> findAll() {
        try{
            return roleRepository.findAll();
        }
        catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No roles found in the database.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later. Thank you for your understanding.");
        }
    }
    public String saveRole(Role role) throws UnexpectedException {
        try {
            roleRepository.save(role);
            return "Role saved Successfully!";
        }catch (DataAccessException e){
            throw new UnexpectedException("The new role could not be saved due to data access problems");
        }catch (InternalError error){
            logger.error(error.getMessage(),error);
            throw new UnexpectedException("We encountered an issue while processing your request. Please try again later.Thank you for your understanding");
        }
    }
public String deleteRoleById(String id) throws UnexpectedException {
        long parseId;
        try{
            parseId=Long.parseLong(id);
            roleRepository.deleteById(parseId);
            return "Record deleted successfully";
        } catch (NumberFormatException e) {
            throw new UnexpectedException("Role  id: " + id + " can't be parsed!");
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException("Role with id:"+ id + "not found! ");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
}
    }


