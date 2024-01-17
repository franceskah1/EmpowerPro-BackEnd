package com.example.empowerprobackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Payroll extends BaseEntity {

    private double salary;
    private double bonus;
    private LocalDate payrollDate;
    //private double  totalSalary;
  //  public void calculateTotal(){
   //    this.totalSalary=this.bonus+this.salary;
   // }

    //private double totalSalary

    @ManyToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user;

}
