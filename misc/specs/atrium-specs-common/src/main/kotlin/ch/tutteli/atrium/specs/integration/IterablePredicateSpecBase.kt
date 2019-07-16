package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

abstract class IterablePredicateSpecBase(verbs: AssertionVerbFactory, spec: Root.() -> Unit) : IterableContainsEntriesSpecBase(verbs, spec) {
    companion object {

        fun Root.nonNullableCases(
            describePrefix: String,
            containsPair: Pair<String, Expect<Iterable<Double>>.(Expect<Double>.() -> Unit) -> Expect<Iterable<Double>>>,
            containsNullablePair: Pair<String, Expect<Iterable<Double?>>.((Expect<Double>.() -> Unit)?) -> Expect<Iterable<Double?>>>,
            action: Suite.(Expect<Iterable<Double>>.(Expect<Double>.() -> Unit) -> Any) -> Unit
        ) {
            describe("$describePrefix describe non-nullable cases") {
                mapOf<String, Expect<Iterable<Double>>.(Expect<Double>.() -> Unit) -> Any>(
                    containsPair.first to { a -> containsPair.second(this, a) },
                    "${containsNullablePair.first} for nullable" to { a ->
                        @Suppress("UNCHECKED_CAST")
                        containsNullablePair.second(this as Expect<Iterable<Double?>>, a)
                    }
                ).forEach { (describe, containsEntriesFunArr) ->
                    describeFun(describe) {
                        action(containsEntriesFunArr)
                    }
                }
            }
        }
    }
}
