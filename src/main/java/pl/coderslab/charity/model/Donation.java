package pl.coderslab.charity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(includeFieldNames = true)
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1)
    private Integer quantity;
    @ManyToMany
    @JoinTable
    private List<Category> categories;

    @ManyToOne
    @JoinColumn
    private  Institution institution;
    @ManyToOne
    private User user;
    private String phone;
    private String street;


    private String city;

    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;


}
