package com.example.lsds_brewster_restservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoanGetAction extends LoanAction {

    public Loan getRequestedLoan(Integer id){
        this.getLoan().setId(id);   // should lazy init Loan object if it doesn't exist already
        if(!isLoanIDValid(this.getLoan()))
            return null;

        Loan foundLoan = this.getDataSource().findLoan(this.getLoan());
        if(foundLoan != null){
            return foundLoan;
        }
        return null;
    }

    protected boolean isLoanIDValid(Loan aLoan){
        if(aLoan.getId() == 0)     // for now, zero id is bad
            return false;

        return true;
    }

    protected String buildSQLStatement(Loan aLoan){
        String statement;

        statement = "SELECT * FROM LOANS WHERE" +
                "ID = " + aLoan.getId().toString();

        return statement;
    }

}
