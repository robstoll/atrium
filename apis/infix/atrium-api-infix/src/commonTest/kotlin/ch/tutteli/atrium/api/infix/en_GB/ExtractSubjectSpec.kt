package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class ExtractSubjectSpec : ch.tutteli.atrium.specs.integration.ExtractSubjectSpec(
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

    @Suppress("unused")
    private fun ambiguityTest() {
        val int: Expect<Int> = notImplemented()
        val nullableInt: Expect<Int?> = notImplemented()
        val star: Expect<*> = notImplemented()

        int extractSubject { _ -> toEqual(1) }
        int extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toEqual(1) }

        nullableInt extractSubject { _ -> toEqual(1) }
        nullableInt extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toEqual(1) }

        star extractSubject { _ -> toBeAnInstanceOf<Int>() }
        star extractSubject withFailureDescription(failureDescription = "custom descr") { _ -> toBeAnInstanceOf<Int>() }
    }
}
