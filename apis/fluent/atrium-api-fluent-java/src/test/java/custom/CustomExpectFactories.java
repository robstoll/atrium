package custom;

import ch.tutteli.atrium.creating.Expect;

public interface CustomExpectFactories {
    static CustomBlockExpect expectCustomBlock(Expect<CustomBlock> coreExpect){
        return new CustomBlockExpect(coreExpect);
    }

    static CustomExceptionExpect expectCustomException(Expect<CustomException> coreExpect){
        return new CustomExceptionExpect(coreExpect);
    }
}
