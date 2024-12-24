package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractExtractSubjectTest
import ch.tutteli.atrium.specs.withNullableSuffix

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

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val int: Expect<Int> = expect(1)
        val nullableInt: Expect<Int?> = expect(1)
        val star: Expect<*> = expect(1)

        int extractSubject { _ -> toEqual(1) }
        int extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toEqual(1) }

        nullableInt extractSubject { _ -> toEqual(1) }
        nullableInt extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toEqual(1) }

        star extractSubject { _ -> toBeAnInstanceOf<Int>() }
        star extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toBeAnInstanceOf<Int>() }
    }
}
