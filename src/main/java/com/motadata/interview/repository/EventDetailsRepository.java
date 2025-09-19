package com.motadata.interview.repository;

import com.motadata.interview.entity.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailsRepository extends JpaRepository<EventDetails, Integer> {

}
