package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public class BlockExpect<SubjectT extends Block> extends AbstractBlockExpect<SubjectT, BlockExpect<SubjectT>> {

    public BlockExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    @Override
    public BlockExpect<SubjectT> createSelf(Expect<SubjectT> coreExpect) {
        return new BlockExpect<>(coreExpect);
    }
}
