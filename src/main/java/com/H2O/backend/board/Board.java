package com.H2O.backend.board;

import com.H2O.backend.hospital.Hospital;
import com.H2O.backend.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(exclude = {"hospital"})
@NoArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no") private Long boardNo;
    @Column(name = "title", nullable = false) private String title;
    @Column(name = "content", nullable = false) private String content;
    @Column(name = "creation_date") private LocalDate creationDate;
    @Column(name = "category", nullable = false) private String category;
    @Column(name = "med_Category", nullable = false) private String medCategory;
    @Column(name = "customer_Category", nullable = false) private String customerCategory;
    @Column(name = "question_Category", nullable = false) private String questionCategory;


    @Column(name = "click") private int click;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_no")
    private Hospital hospital;


    @Builder
    public Board(String title, String content,String medCategory,
                 LocalDate creationDate, String category,String customerCategory,
                 String questionCategory, int click){
        this.title=title;
        this.content=content;
        this.creationDate=creationDate;
        this.category=category;
        this.medCategory=medCategory;
        this.customerCategory=customerCategory;
        this.questionCategory=questionCategory;
        this.click=click;
    }
}
