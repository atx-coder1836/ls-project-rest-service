package com.example.lsds_brewster_restservice;

public class LoanAction {
    private Loan loan;
    private ListDBSingleton dataSource;

    public LoanAction(){
        this.dataSource = ListDBSingleton.getInstance();
    }

    public Loan getLoan() {
        //lazy init loan
        if (loan == null)
            loan = new Loan();

        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public ListDBSingleton getDataSource() {
        return dataSource;
    }

    public void setDataSource(ListDBSingleton dataSource) {
        this.dataSource = dataSource;
    }
}
