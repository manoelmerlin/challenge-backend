package com.backendchallenge.challenge.repository;

import com.backendchallenge.challenge.model.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {

    @Query("select obj from Withdraw obj where obj.user.id = :userId and obj.createdAt >= :createdAtStart and obj.createdAt <= now()")
    List<Withdraw> getWithdrawsByIdInLastDay(@Param("userId") Integer id,
                                                           @Param("createdAtStart") LocalDateTime startDate
                                                          );
    long countByUserId(Integer id);
}
