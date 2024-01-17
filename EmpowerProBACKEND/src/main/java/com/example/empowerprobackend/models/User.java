package com.example.empowerprobackend.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate hireDate;
    private String address;
    private LocalDate birthdate;
    private String birthplace;
    private String userName;
    private String gender;
    @OneToMany
    public List<LeaveRequests>leaveRequests;
    @OneToMany
    public List<LeaveBalance>leaveBalances;

    @Lob
    @Column(length = 2147483647)
    private byte[] photo;
    private String FileName;
    private String FileType;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired;
    private LocalDateTime forgetPasswordTokenCreationDate;
    private String forgetPasswordToken;
    private String confirmationToken;
    private LocalDateTime tokenCreationDate;
    private LocalDateTime tokenConfirmationDate;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

   @ManyToOne
   @JoinColumn(name="departments_id",referencedColumnName = "id")
   private Departments departments;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

}

