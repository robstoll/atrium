package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.specs.integration.AbstractIterableToHaveElementsAndAnyExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableToHaveElementsAndAnyExpectationsSequenceTest :
    AbstractIterableToHaveElementsAndAnyExpectationsTest(
        getToContainSequencePair(),
        getToContainNullableSequencePair().withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        private fun getToContainSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().$toContain" to Companion::toContainInAnyOrderEntrySequence

        private fun toContainInAnyOrderEntrySequence(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect._logic.changeSubject.unreported { it.asSequence() }.asIterable().toContain(a)

        private fun getToContainNullableSequencePair() =
            "asSequence().${Sequence<*>::asIterable.name}().$toContain" to Companion::toContainNullableEntrySequence

        private fun toContainNullableEntrySequence(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect._logic.changeSubject.unreported { it.asSequence() }.asIterable { toContain(a) }.asIterable()
    }
}
