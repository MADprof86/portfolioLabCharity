package pl.coderslab.charity.model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    @Lob
    private String description;
}
