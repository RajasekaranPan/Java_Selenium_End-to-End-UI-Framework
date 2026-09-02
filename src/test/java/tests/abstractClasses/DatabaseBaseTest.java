package tests.abstractClasses;

import database.DatabaseManager;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import config.ConfigReader;

public abstract class DatabaseBaseTest {

    @BeforeClass(alwaysRun = true)
    public void skipRemoteExecution() {

        if ("remote".equalsIgnoreCase(ConfigReader.getExecution())) {
            throw new SkipException(
                    "Database tests are excluded from remote execution."
            );
        }
    }
    
    @BeforeSuite
    public void initializeDatabase() {
    	
    	  	if (isRemoteExecution()) {
              return;
          }
        DatabaseManager.initialize();
    }

    @AfterSuite
    public void closeDatabase() {
    	
    	 if (isRemoteExecution()) {
             return;
         }
        DatabaseManager.closePool();
    }
    
    private boolean isRemoteExecution() {
        return "remote".equalsIgnoreCase(
                ConfigReader.getExecution()
        );
    }
}