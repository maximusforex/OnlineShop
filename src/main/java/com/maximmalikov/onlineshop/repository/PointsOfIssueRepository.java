package com.maximmalikov.onlineshop.repository;

import com.maximmalikov.onlineshop.domain.PointsOfIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsOfIssueRepository extends JpaRepository<PointsOfIssue, Long> {

    PointsOfIssue getByPointsId(long pointsId);

    PointsOfIssue getByAddress(String address);

}
