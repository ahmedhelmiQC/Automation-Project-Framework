package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.DriverFactory.getDriver;


public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(
            IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }


   public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
       File logtFile = Utility.getLastFile(LogsUtils.LOGS_PATH);
       try {
           Allure.addAttachment("Logs.log", Files.readString(Path.of(logtFile.getPath())));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       if (testResult.getStatus()==ITestResult.FAILURE)
          LogsUtils.info("Test Case " + testResult.getName() + "failed");
      Utilities.Utility.takeScreenShoot(getDriver(),testResult.getName());
    }

    }
