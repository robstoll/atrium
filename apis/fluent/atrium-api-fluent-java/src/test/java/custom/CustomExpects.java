package custom;

import static ch.tutteli.atrium.api.fluent.java.Expects.createExpect;

public interface CustomExpects {
    static AbstractCustomBlockExpect<CustomBlock, ?> expect(CustomBlock subject) {
        return CustomExpectFactories.expectCustomBlock(createExpect(subject));
    }

    static AbstractCustomExceptionExpect<CustomException, ?> expect(CustomException subject) {
        return CustomExpectFactories.expectCustomException(createExpect(subject));
    }
}
