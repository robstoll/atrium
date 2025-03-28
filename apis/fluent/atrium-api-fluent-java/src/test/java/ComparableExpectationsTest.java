import org.junit.jupiter.api.Test;

import static ch.tutteli.atrium.api.fluent.java.Expectations.expect;

public class ComparableExpectationsTest {

    @Test
    public void testToBe() {
        expect(10).toBeLessThan(3);
    }
}
