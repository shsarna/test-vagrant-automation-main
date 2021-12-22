package selenium_framework;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;
import utils.AllureUtils;

import java.util.Map;

public class SoftAssertWrapper extends SoftAssert {
    WebDriver driver;
    boolean allAssertScreenshot;
    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
    private static final String DEFAULT_SOFT_ASSERT_MESSAGE = "The following asserts failed:";

    public SoftAssertWrapper(WebDriver driver, boolean allAssertScreenshot) {
        this.driver = driver;
        this.allAssertScreenshot = allAssertScreenshot;
    }

    @Override
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            a.doAssert();
            onAssertSuccess(a);
            if(allAssertScreenshot)
                AllureUtils.stepScreenshot(driver, "PASS", "");
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            m_errors.put(ex, a);
            AllureUtils.stepScreenshot(driver, "FAIL", a.getMessage());
        } finally {
            onAfterAssert(a);
        }
    }

    public void assertAll() {
        assertAll(null);
    }

    public void assertAll(String message) {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder(null == message ? DEFAULT_SOFT_ASSERT_MESSAGE : message);
            boolean first = true;
            for (AssertionError error : m_errors.keySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(getErrorDetails(error));
            }
            throw new AssertionError(sb.toString());
        }
    }
}
