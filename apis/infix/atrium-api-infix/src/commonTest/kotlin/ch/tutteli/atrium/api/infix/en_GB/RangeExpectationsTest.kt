package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractRangeExpectationsTest

class RangeExpectationsTest : AbstractRangeExpectationsTest(
    toBeInRangeIntSpec = fun1(Expect<Int>::toBeIn),
    toBeInRangeLongSpec = fun1(Expect<Long>::toBeIn),
    toBeInRangeCharSpec = fun1(Expect<Char>::toBeIn),
)
