package com.example.lsds_brewster_restservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/loan/update")
public class LSDSRestServiceLoanUpdateResource {
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateLoan(Loan theLoan) {
        if (theLoan == null)
            return "Error: no loan supplied for update";     // or maybe i'd throw an exception?

        LoanUpdateAction luAction = new LoanUpdateAction();
        Loan updatedLoan = luAction.updateLoan(theLoan);

        if(updatedLoan == null)
            return LSDSRestServiceApplication.getLastError();

        return "Successfully updated loan: " + updatedLoan.toString();
    }
}
