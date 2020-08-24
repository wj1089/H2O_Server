package com.H2O.backend.ambulance;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
interface IAmbulanceRepositoryy{

        }

public class AmbulanceRepositoryImpl extends QuerydslRepositorySupport implements IAmbulanceRepositoryy {


    public AmbulanceRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Ambulance.class);
    }
}
