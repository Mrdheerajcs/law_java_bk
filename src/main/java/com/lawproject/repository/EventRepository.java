package com.lawproject.repository;

import com.lawproject.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.status = :status WHERE e.eventId = :id")
    int updateStatus(@Param("id") Long id, @Param("status") String status);

    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.status = :status WHERE e.eventId IN :ids")
    int bulkUpdateStatus(@Param("ids") List<Long> ids, @Param("status") String status);
}