package com.example.Junit_Crud.TestExceptions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
public class TestingExceptions {
	
	    private String getName()
	    {
	        return null;
	    }

	
	    @Test(expected = NullPointerException.class)
	    public void testNullPointerException()
	    {
	        String name = getName();
	        System.out.println(name.length());
	    }
	    
	   	    
	    @Test
	    public void testWithTryAndCatchNullPointerException() {
	        String name = getName();
	        try {
	            System.out.println(name.length());
	            fail();
	        } catch (NullPointerException ex) {
	            assertTrue(ex instanceof NullPointerException);
	        }
	    }


}
