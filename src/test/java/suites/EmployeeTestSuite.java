package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import tests.EmployeeTest;
import tests.ProductTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({EmployeeTest.class})
public class EmployeeTestSuite {
}
