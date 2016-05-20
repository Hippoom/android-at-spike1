package com.deppon.app.june.util;

import java.util.Set;
import android.os.Bundle;
import android.test.InstrumentationTestRunner;

public class DepponTestRunner extends InstrumentationTestRunner {


    public void onCreate(Bundle arguments) {

        if (null != arguments) {    
            
            Set<String> keySet = arguments.keySet();  //获取所有的Key,
            
            for(String key : keySet){  //bundle.get(key);来获取对应的value 
            	Logger.v("onCreate" );
            	Logger.v(arguments.getString(key) );
            	
            }
        }    
        super.onCreate(arguments);
    }
    
  /* public TestSuite getAllTests() {
    	TestSuite suite = new TestSuite("Sweet");
        try {
			suite.addTest(new depponAddressbook());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        return suite;
    }

    
    *//** @hide pending API Council approval *//*
    public void parseTestClass(String testClassName, TestSuiteBuilder testSuiteBuilder) {
        int methodSeparatorIndex = testClassName.indexOf('#');
        String testMethodName = null;

        if (methodSeparatorIndex > 0) {
            testMethodName = testClassName.substring(methodSeparatorIndex + 1);
            testClassName = testClassName.substring(0, methodSeparatorIndex);
        }
//        testSuiteBuilder.addTestClassByName(testClassName, testMethodName, getTargetContext());
    }*/
}