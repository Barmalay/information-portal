package com.mai.webApplication.repositories;

import com.mai.webApplication.models.Statement;
import com.mai.webApplication.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Integer> {
    List<Statement> findStatementsByTeacherEquals(Teacher teacher);
}
