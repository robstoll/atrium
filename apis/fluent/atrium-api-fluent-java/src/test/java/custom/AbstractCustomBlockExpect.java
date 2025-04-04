package custom;

import ch.tutteli.atrium.api.fluent.java.AbstractBlockExpect;
import ch.tutteli.atrium.creating.Expect;

public abstract class AbstractCustomBlockExpect<SubjectT extends CustomBlock, SelfT extends AbstractCustomBlockExpect<SubjectT, SelfT>>
    extends AbstractBlockExpect<SubjectT, SelfT> {

    public AbstractCustomBlockExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    public CustomExceptionExpect toThrowCustomException() {
        return toThrow(CustomException.class).asInstanceOf(CustomExpectFactories::expectCustomException);
    }
}
