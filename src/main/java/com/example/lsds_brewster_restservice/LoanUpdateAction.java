package com.example.lsds_brewster_restservice;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoanUpdateAction extends LoanAction {

    public Loan updateLoan(Loan theLoan){
        if( theLoan == null){
            System.out.println("Null loan passed to updateLoan");
            LSDSRestServiceApplication.setLastError("updateLoan(): Null loan passed into function");
            return null;
        }

        if( !this.isLoanGoodToUpdate(theLoan)){
            System.out.println("The loan passed to updateLoan was invalid");
            LSDSRestServiceApplication.setLastError("updateLoan(): The loan passed into the function was invalid");
            return null;
        }

        return this.getDataSource().updateLoan(theLoan);
    }

    protected boolean isLoanGoodToUpdate(Loan aLoan){
        // loan has to have non-zero ID and be fully formed
        System.out.println("Validating loan for update: " + aLoan.toString());
        Integer id = aLoan.getId();
        if( (id == null) || id == 0)
            return false;
        if( aLoan.getAmount().isEmpty() || aLoan.getRate().isEmpty() ||
                aLoan.getTerm().isEmpty() || aLoan.getPayment().isEmpty()){
            return false;
        }

        return true;
    }

    protected String buildSQLUpdateStatement( Loan aLoan ) {
        String statement;
        ArrayList<String> setClause = new ArrayList<String>();

        if (!aLoan.getAmount().isEmpty())
            setClause.add( "AMOUNT = " + aLoan.getAmount());
        if (!aLoan.getRate().isEmpty())
            setClause.add( "RATE = " + aLoan.getRate());
        if (!aLoan.getTerm().isEmpty())
            setClause.add( "TERM = " + aLoan.getTerm());
        if (!aLoan.getPayment().isEmpty())
            setClause.add( "PAYMENT = " + aLoan.getPayment());

        statement = "UPDATE LOANS" +
                String.join(",", setClause) +
                "WHERE ID = " + aLoan.getId().toString();
        return statement;
    }
}
