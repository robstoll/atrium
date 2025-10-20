package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractExtractSubjectTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class ExtractSubjectTest : AbstractExtractSubjectTest(
    fun2(Companion::extractSubject),
    fun2(Companion::extractSubjectNullable).withNullableSuffix(),
    extractSubjectDefaultFailureDescription = "❗❗ subject extraction not possible, previous expectation failed, cannot show sub-expectations"
) {
    companion object {
        fun extractSubject(
            expect: Expect<Int>,
            failureDescription: String?,
            assertionCreator: Expect<Int>.(Int) -> Unit
        ) =
            if (failureDescription == null) expect extractSubject assertionCreator
            else expect extractSubject withFailureDescription(failureDescription) { subject ->
                assertionCreator(subject)
            }

        fun extractSubjectNullable(
            expect: Expect<Int?>,
            failureDescription: String?,
            assertionCreator: Expect<Int?>.(Int?) -> Unit
        ) =
            if (failureDescription == null) expect extractSubject assertionCreator
            else expect extractSubject withFailureDescription(failureDescription) { subject ->
                assertionCreator(subject)
            }
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var int: Expect<Int> = expect(1)
        var nullableInt: Expect<Int?> = expect(1)
        var star: Expect<*> = expect(1)

        int = int extractSubject { _ -> toEqual(1) }
        int = int extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toEqual(1) }

        nullableInt = nullableInt extractSubject { _ -> toEqual(1) }
        nullableInt =
            nullableInt extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toEqual(1) }

        star = star extractSubject { _ -> toBeAnInstanceOf<Int>() }
        star =
            star extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toBeAnInstanceOf<Int>() }
    }
}
