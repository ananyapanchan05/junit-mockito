package com.example.Junit_Crud.TestExceptions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ExceptionTestWithRule {
	    @Rule
	    public ExpectedException thrown = ExpectedException.none();
	    
	    @Test 
	    public void testUsingTheRuleNullPointerException()
	    {
	        thrown.expect(NullPointerException.class);
	        String name = getName();
	        System.out.println(name.length());
	    }
	    private String getName() {
	        return null;
	    }
	
}
