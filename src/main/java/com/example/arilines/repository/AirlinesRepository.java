package com.example.arilines.repository;

import com.example.arilines.model.Airlines;
import com.speedment.jpastreamer.application.JPAStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AirlinesRepository extends JpaRepository<Airlines,Long> {

}
