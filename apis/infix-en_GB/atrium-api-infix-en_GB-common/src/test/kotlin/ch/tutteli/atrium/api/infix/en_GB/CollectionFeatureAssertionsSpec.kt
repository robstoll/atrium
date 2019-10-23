package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property

class CollectionFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionFeatureAssertionsSpec(
    property<Collection<String>, Int>(Expect<Collection<String>>::size),
    fun1<Collection<String>, Expect<Int>.() -> Unit>(Expect<Collection<String>>::size).name to Companion::size
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Collection<Int>> = notImplemented()
        var a2: Expect<out Collection<Int>> = notImplemented()

        a1.size
        a1 = a1.size { }
        a2.size
        a2 = a2.size { }
    }

    companion object {
        fun size(plant: Expect<Collection<String>>, assertionCreator: Expect<Int>.() -> Unit)
            = plant size { assertionCreator() }
    }
}
