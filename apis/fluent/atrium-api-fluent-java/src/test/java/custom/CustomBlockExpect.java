package custom;

import ch.tutteli.atrium.creating.Expect;

public class CustomBlockExpect extends AbstractCustomBlockExpect<CustomBlock, CustomBlockExpect> {

    public CustomBlockExpect(Expect<CustomBlock> expect) {
        super(expect);
    }

    public CustomBlockExpect createSelf(Expect<CustomBlock> coreExpect) {
        return new CustomBlockExpect(coreExpect);
    }
}
