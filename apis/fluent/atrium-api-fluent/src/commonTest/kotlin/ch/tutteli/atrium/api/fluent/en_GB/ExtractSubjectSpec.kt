package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class ExtractSubjectSpec : ch.tutteli.atrium.specs.integration.ExtractSubjectSpec(
    fun2(Expect<Int>::extractSubject),
    fun2(Expect<Int?>::extractSubject).withNullableSuffix(),
    extractSubjectDefaultFailureDescription = "❗❗ subject extraction not possible, previous expectation failed, cannot show sub-expectations"
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val int: Expect<Int> = notImplemented()
        val nullableInt: Expect<Int?> = notImplemented()
        val star: Expect<*> = notImplemented()

        int.extractSubject { _ -> toEqual(1) }
        int.extractSubject(failureDescription = "custom descr") { _ -> toEqual(1) }

        nullableInt.extractSubject { _ -> toEqual(1) }
        nullableInt.extractSubject(failureDescription = "custom descr") { _ -> toEqual(1) }

        star.extractSubject { _ -> toBeAnInstanceOf<Int>() }
        star.extractSubject(failureDescription = "custom descr") { _ -> toBeAnInstanceOf<Int>() }
    }
}
