package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ProductTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ProductTest.class})
public class ProductTestSuite {
// I see only 3 tests executed from ProductTest. Where is the getProduct test?
}
