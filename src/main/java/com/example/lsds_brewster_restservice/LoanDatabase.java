package com.example.lsds_brewster_restservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class LoanDatabase {

    private static LoanDatabase instance = new LoanDatabase();
    private Connection loanDbConnection;
    private ArrayList<String> fauxDatabase;
    private Integer nextLoanID;

    // make constructor private
    private LoanDatabase(){}

    private void createDatabaseStructure(){
        //We need this to create the database structure
        //To be safe we probably should see if this exists already
        try {
            instance.loanDbConnection.createStatement().execute("CREATE TABLE LOANS (" +
                    "ID INT NOT NULL PRIMARY KEY," +
                    "AMOUNT VARCHAR(255)," +
                    "RATE VARCHAR(255)," +
                    "TERM VARCHAR(255)," +
                    "PAYMENT VARCHAR(255));");
            instance.loanDbConnection.commit();
        }
        catch (SQLException sqlException){
            System.out.println("System exception trying to create LOANS table: " + sqlException.getMessage());
        }
    }

    public static LoanDatabase getInstance() {
        Logger logger = Logger.getLogger(instance.getClass().getName());
 //       if (instance.loanDbConnection == null){
        if(instance.fauxDatabase == null) {
            // establish a connection to actually create the db
//            try {
//                logger.info("db connection is null so let's set it up");
                instance.fauxDatabase = new ArrayList<String>();

                //javax.naming.Context context = new javax.naming.InitialContext();
                //javax.sql.DataSource dataSource = (javax.sql.DataSource)context.lookup("DerbyPool");
                //instance.loanDbConnection = dataSource.getConnection();
                //logger.info(instance.loanDbConnection.toString());
                //instance.createDatabaseStructure();
                instance.nextLoanID = 100;
 //           }
 //           catch (SQLException sqlException){
 //               SQLException nested;
 //              logger.severe("Exception from creating the DB: " + sqlException.getMessage());
 //               nested = sqlException.getNextException();
 //               if ( nested != null ){
 //                   logger.severe(nested.getMessage());
 //               }
 //           } catch (NamingException e) {
 //               throw new RuntimeException(e);
         }
        return instance;
    }

    public Connection getLoanDbConnection(){
            return instance.loanDbConnection;
    }
    public ArrayList<String> getFauxDatabase(){
        return instance.fauxDatabase;
    }
    public Integer getNextLoanID () {
        return instance.nextLoanID++;
    }
    public String findLoan(Loan aLoan){
        Iterator<String> iterator = this.fauxDatabase.iterator();

        while(iterator.hasNext()){
            String record = iterator.next();
            String[] tokens = record.split("|");
            if(Integer.parseInt(tokens[0]) == aLoan.getId())
                return record;
        }
        return "";
    }

    public String updateLoan(Loan aLoan){
        Boolean found = false;
        String record;
        Iterator<String> iterator = this.fauxDatabase.iterator();
        while(iterator.hasNext() && !found) {
            record = iterator.next();
            String[] tokens = record.split("|");
            if (Integer.parseInt(tokens[0]) == aLoan.getId()) {
                found = true;
                int index = this.fauxDatabase.lastIndexOf(record);
                this.fauxDatabase.set(index, this.loanToRecord(aLoan));
            }
        }
        return loanToRecord(aLoan);
    }

    protected Loan recordToLoan(String record){
        Loan aLoan = new Loan();
        String[] tokens = record.split("|");
        aLoan.setId(Integer.parseInt(tokens[0]));
        aLoan.setAmount(tokens[1]);
        aLoan.setRate(tokens[2]);
        aLoan.setTerm(tokens[3]);
        aLoan.setPayment(tokens[4]);
        return aLoan;
    }

    protected String loanToRecord(Loan aLoan){
        String record = String.join("|",aLoan.getId().toString(),
                aLoan.getAmount(),
                aLoan.getRate(),
                aLoan.getTerm(),
                aLoan.getPayment());
        return record;
    }
}
