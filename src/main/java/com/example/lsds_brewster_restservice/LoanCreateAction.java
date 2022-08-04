package com.example.lsds_brewster_restservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LoanCreateAction extends LoanAction {

    public Loan createNewLoan(Loan aLoan) {
        Statement statement;

        // probably should validate our data here
        //for now let's assume the loan is fully populated
        this.setLoan(aLoan);
        // LoanID should be zero here. We can accept with populated ID but risk collisions
        //maybe we check for that case
        if(this.getLoan().getId() != 0)
            System.out.println("Risking collision with pre-popluated ID");

        //what if that ID is already in use?
        if(this.getDataSource().findLoan(this.getLoan()) != null) {
            System.out.println("The loan ID you are trying to create already exists: " + aLoan.toString());
            LSDSRestServiceApplication.setLastError("Trying to create loan with ID already in use: "+ aLoan.toString());
            return null;
        }

        // assume all good now
        return this.getDataSource().addLoan(this.getLoan());
    }

    protected String buildSQLStatement(){
        String statement;
        statement = "INSERT INTO LOANS (ID, AMOUNT, RATE, TERM, PAYMENT)" +
                "VALUES(" +
                getLoan().getId().toString() + "," +
                getLoan().getAmount() + "," +
                getLoan().getRate() + "," +
                getLoan().getTerm()  + "," +
                getLoan().getPayment() + ");";
        return statement;
    }

    protected String buildStringRecord(){
        String record;
        record = getLoan().getId().toString() + "|" +
                getLoan().getAmount() + "|" +
                getLoan().getRate() + "|" +
                getLoan().getTerm() + "|" +
                getLoan().getPayment();
        return record;
    }
}
