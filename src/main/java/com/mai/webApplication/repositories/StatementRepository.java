package com.mai.webApplication.repositories;

import com.mai.webApplication.models.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Integer> {
}
