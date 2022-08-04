package com.example.lsds_brewster_restservice;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/loan/create")
public class LSDSRestServiceLoanCreateResource {
    LoanCreateAction lcAction;
    Loan newLoan;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createLoan(Loan theLoan) {
        if (theLoan == null)
            return "Error - no loan supplied";     // or maybe i'd throw an exception?

        lcAction = new LoanCreateAction();
        newLoan = lcAction.createNewLoan(theLoan);
        if(newLoan == null)
            return LSDSRestServiceApplication.getLastError();

        return "I created a new loan: " + newLoan.toString();
    }
}
