package com.abc.hotel.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String city;
    private String userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @JsonManagedReference
    List<Room> rooms;

    @Override
    public String toString() {
        return "";
    }
}
