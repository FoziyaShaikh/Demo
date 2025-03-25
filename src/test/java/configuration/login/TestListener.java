package configuration.login;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends TestBase implements ITestListener {

    public TestListener() throws IOException {
        super();
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("+ Begin test suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("- End test suite: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + getTestMethodName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + getTestMethodName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + getTestMethodName(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + getTestMethodName(result));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + getTestMethodName(result));
    }
}
