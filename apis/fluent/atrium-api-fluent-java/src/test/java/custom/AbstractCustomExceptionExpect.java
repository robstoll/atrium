package custom;

import ch.tutteli.atrium.api.fluent.java.AbstractAnyExpect;
import ch.tutteli.atrium.api.fluent.java.StringExpect;
import ch.tutteli.atrium.creating.Expect;

public abstract class AbstractCustomExceptionExpect<SubjectT extends CustomException, SelfT extends AbstractCustomExceptionExpect<SubjectT, SelfT>>
    extends AbstractAnyExpect<SubjectT, SelfT> {

    public AbstractCustomExceptionExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    public StringExpect getErrorCode() {
        return feature("errorCode", CustomException::getErrorCode).asInstanceOf(StringExpect::new);
    }
}
