package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.testutils.WithAsciiReporter
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property

class PairFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.PairFeatureAssertionsSpec(
    property<Pair<String, Int>, String>(Expect<Pair<String, Int>>::first),
    fun1<Pair<String, Int>, Expect<String>.() -> Unit>(Expect<Pair<String, Int>>::first).name to Companion::first,
    property<Pair<String, Int>, Int>(Expect<Pair<String, Int>>::second),
    fun1<Pair<String, Int>, Expect<Int>.() -> Unit>(Expect<Pair<String, Int>>::second).name to Companion::second,
    property<Pair<String?, Int?>, String?>(Expect<Pair<String?, Int?>>::first),
    fun1<Pair<String?, Int?>, Expect<String?>.() -> Unit>(Expect<Pair<String?, Int?>>::first).name to Companion::firstNullable,
    property<Pair<String?, Int?>, Int?>(Expect<Pair<String?, Int?>>::second),
    fun1<Pair<String?, Int?>, Expect<Int?>.() -> Unit>(Expect<Pair<String?, Int?>>::second).name to Companion::secondNullable
) {
    companion object : WithAsciiReporter() {
        fun first(expect: Expect<Pair<String, Int>>, assertionCreator: Expect<String>.() -> Unit) =
            expect first { assertionCreator() }

        fun second(expect: Expect<Pair<String, Int>>, assertionCreator: Expect<Int>.() -> Unit) =
            expect second { assertionCreator() }

        fun firstNullable(expect: Expect<Pair<String?, Int?>>, assertionCreator: Expect<String?>.() -> Unit) =
            expect first { assertionCreator() }

        fun secondNullable(expect: Expect<Pair<String?, Int?>>, assertionCreator: Expect<Int?>.() -> Unit) =
            expect second { assertionCreator() }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Pair<String, Int>> = notImplemented()
        var a2: Expect<Pair<String?, Int>> = notImplemented()
        var a3: Expect<Pair<String, Int?>> = notImplemented()
        var a4: Expect<Pair<String?, Int?>> = notImplemented()
        var star: Expect<Pair<*, *>> = notImplemented()

        a1.first
        a2.first
        a3.first
        a4.first
        star.first
        a1 = a1 first { }
        a2 = a2 first { }
        a3 = a3 first { }
        a4 = a4 first { }
        star = star first { }

        a1.second
        a2.second
        a3.second
        a4.second
        star.second
        a1 = a1 second { }
        a2 = a2 second { }
        a3 = a3 second { }
        a4 = a4 second { }
        star = star second { }
    }
}
