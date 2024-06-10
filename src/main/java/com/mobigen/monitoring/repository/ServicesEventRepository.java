package com.mobigen.monitoring.repository;

import com.mobigen.monitoring.dto.ServicesEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesEventRepository extends JpaRepository<ServicesEvent, Long> {
    List<ServicesEvent> findTopByOrderByEventOccurredAtDesc(Pageable pageable);
}
