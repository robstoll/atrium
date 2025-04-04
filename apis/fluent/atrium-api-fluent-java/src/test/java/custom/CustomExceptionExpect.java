package custom;

import ch.tutteli.atrium.creating.Expect;

public class CustomExceptionExpect extends AbstractCustomExceptionExpect<CustomException, CustomExceptionExpect> {

    public CustomExceptionExpect(Expect<CustomException> expect) {
        super(expect);
    }

    public CustomExceptionExpect createSelf(Expect<CustomException> coreExpect) {
        return new CustomExceptionExpect(coreExpect);
    }
}
