package com.mai.webApplication.services;

import com.mai.webApplication.models.Statement;
import com.mai.webApplication.models.Student;
import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.repositories.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StatementService {

    private final StatementRepository statementRepository;

    @Autowired
    public StatementService(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    @Transactional
    public void register(Statement statement) {
        statementRepository.save(statement);
    }

    public List<Statement> findAllStatements(){
        return statementRepository.findAll();
    }

    public List<Statement> findAllStudentStatements(Student student){
        List<Statement> statements = findAllStatements();
        statements.removeIf(statement -> statement.getStudent().getId() != student.getId());
        return statements;
    }

    public List<Statement> findAllTeacherStatements(Teacher teacher){
        return statementRepository.findStatementsByTeacherEquals(teacher);
    }
}
