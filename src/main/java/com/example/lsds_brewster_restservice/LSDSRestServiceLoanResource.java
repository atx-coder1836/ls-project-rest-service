package com.example.lsds_brewster_restservice;

import jakarta.ws.rs.*;

import java.lang.String;


@Path("/loan")
public class LSDSRestServiceLoanResource {
    @GET
    @Produces("text/plain")
    public String getLoan(@QueryParam("id") String loanId) {
        Loan foundLoan;
        if (loanId == null) {
            return "No Loan ID supplied";
        }
        else if (loanId.isEmpty()) {
            return "Loan ID supplied is empty";
        }
        LoanGetAction action = new LoanGetAction();
        foundLoan = action.getRequestedLoan(Integer.valueOf(loanId));
        if( foundLoan == null )
            return "Could not find loan: " + loanId;
        return foundLoan.toString();
    }
}
