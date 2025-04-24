package ch.tutteli.atrium.api.fluent.logic.based.en_GB

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

    @Test
    fun ambiguityTest() {
        val int: Expect<Int> = expect(1)
        val nullableInt: Expect<Int?> = expect(1)
        val star: Expect<*> = expect(1)

        int.extractSubject { _ -> toEqual(1) }
        int.extractSubject(failureDescription = "custom descr") { _ -> toEqual(1) }

        nullableInt.extractSubject { _ -> toEqual(1) }
        nullableInt.extractSubject(failureDescription = "custom descr") { _ -> toEqual(1) }

        star.extractSubject { _ -> toBeAnInstanceOf<Int>() }
        star.extractSubject(failureDescription = "custom descr") { _ -> toBeAnInstanceOf<Int>() }
    }
}
