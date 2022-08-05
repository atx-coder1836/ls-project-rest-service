package com.example.lsds_brewster_restservice.tests;
import com.example.lsds_brewster_restservice.ListDBSingleton;
import com.example.lsds_brewster_restservice.Loan;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListDBSingletonTests {
    ListDBSingleton db;

    @BeforeAll
    public void initialize(){
        // let's get the db instance setup
        this.db = ListDBSingleton.getInstance();
    }

    @AfterAll
    public void tearDown(){
        //not sure this is needed, but...
        this.db = null;
    }

    @Test
    public void testAddLoan(){
        Loan aLoan = new Loan(0,"111,111.00","5%","60","12,345.00");
        Loan addedLoan = db.addLoan(aLoan);
        // As initial test, the added loan should have a real ID now
        Assertions.assertNotEquals(0,addedLoan.getId());
    }
}
