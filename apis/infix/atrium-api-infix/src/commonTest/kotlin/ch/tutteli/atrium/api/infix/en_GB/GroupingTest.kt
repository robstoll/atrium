package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun3

class GroupingTest : ch.tutteli.atrium.specs.integration.GroupingTest(
    fun3(Expect<Int>::group)
)
