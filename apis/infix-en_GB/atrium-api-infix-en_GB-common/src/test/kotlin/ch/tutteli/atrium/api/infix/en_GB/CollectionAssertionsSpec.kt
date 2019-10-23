package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name

class CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    fun1<Collection<Int>, Empty>(Expect<Collection<Int>>::toBe).name to Companion::isEmpty,
    fun1<Collection<Int>, Empty>(Expect<Collection<Int>>::notToBe).name to Companion::isNotEmpty
) {
    companion object {
        fun isEmpty(expect: Expect<Collection<Int>>) = expect toBe Empty
        fun isNotEmpty(expect: Expect<Collection<Int>>) = expect notToBe Empty
    }
}
