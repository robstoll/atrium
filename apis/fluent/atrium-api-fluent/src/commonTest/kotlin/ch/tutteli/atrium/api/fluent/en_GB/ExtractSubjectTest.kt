package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractExtractSubjectTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class ExtractSubjectTest : AbstractExtractSubjectTest(
    fun2(Expect<Int>::extractSubject),
    fun2(Expect<Int?>::extractSubject).withNullableSuffix(),
    extractSubjectDefaultFailureDescription = "❗❗ subject extraction not possible, previous expectation failed, cannot show sub-expectations"
) {

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var int: Expect<Int> = expect(1)
        var nullableInt: Expect<Int?> = expect(1)
        var star: Expect<*> = expect(1)

        int = int.extractSubject { _ -> toEqual(1) }
        int = int.extractSubject(failureDescription = "custom descr") { _ -> toEqual(1) }

        nullableInt = nullableInt.extractSubject { _ -> toEqual(1) }
        nullableInt = nullableInt.extractSubject(failureDescription = "custom descr") { _ -> toEqual(1) }

        star = star.extractSubject { _ -> toBeAnInstanceOf<Int>() }
        star = star.extractSubject(failureDescription = "custom descr") { _ -> toBeAnInstanceOf<Int>() }
    }
}
