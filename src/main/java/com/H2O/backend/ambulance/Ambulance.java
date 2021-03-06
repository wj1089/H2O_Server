package com.H2O.backend.ambulance;

import com.H2O.backend.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ambulance")
public class Ambulance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ambulance_no") private Long ambulanceNo;
    @Column(name = "ambulance_name", nullable = false) private String ambulanceName;
    @Column(name = "addr", nullable = false) private String addr;
    @Column(name = "tel", nullable = false) private String tel;
    @Column(name = "homePage", nullable = false) private String homePage;
    @Column(name = "count", nullable = false) private int count;
    @Column(name = "starting_addr", nullable = false) private String starting_addr;
    @Column(name = "ending_addr", nullable = false) private String ending_addr;
    @Column(name = "postcode", nullable = false) private String postcode;


    @OneToMany(mappedBy = "ambulance")
    private List<Reservation> reservations;

    @Builder
    public Ambulance(String ambulanceName, String addr, String tel,
                     String homePage, int count, String starting_addr,
                     String ending_addr, String postcode){

        this.ambulanceName = ambulanceName;
        this.addr = addr;
        this.tel = tel;
        this.homePage = homePage;
        this.count = count;
        this.starting_addr = starting_addr;
        this.ending_addr = ending_addr;
        this.postcode = postcode;
    }
}
