package com.example.lsds_brewster_restservice;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class LSDSRestServiceApplication extends Application {
    // establish the db instance when the app loads? or do we wait until any given action?
    LoanDatabase loanDb = LoanDatabase.getInstance();
    public static String lastError;

    public LSDSRestServiceApplication(){}

    public static String getLastError(){
        return lastError;
    }

    public static void setLastError(String error){
        lastError = error;
    }

}