package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.include

abstract class MapAsIterableAssertionsSpec(
    verbs: AssertionVerbFactory,
    asEntriesFunName: String,
    asEntries: Assert<Map<String, Int>>.() -> Assert<Set<Map.Entry<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Map<String, Int>>("$describePrefix[$asEntriesFunName] ",
        asEntriesFunName to mapToCreateAssertion { asEntries(this) }
    ) {})

    val holdingSubject: Map<String, Int> = mapOf("a" to 1, "c" to 3, "e" to 5, "g" to 7)
    val failingSubject: Map<String, Int> = mapOf("b" to 2, "d" to 4, "f" to 6, "h" to 8)

    include(object : CheckingAssertionSpec<Map<String, Int>>(
        verbs, "$describePrefix[$asEntriesFunName] ",
        checkingTriple(
            asEntriesFunName,
            { asEntries(this).contains { keyValue("g", 7) } },
            holdingSubject,
            failingSubject
        )
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    describeFun(asEntriesFunName) {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(mapOf(1 to "a", 2 to "b")).asEntries().contains.inAnyOrder.only.entries(
                { keyValue(2, "b") },
                { keyValue(1, "a") }
            )
        }
    }
})

//TODO replace with keyValue from API once it is implemented
private fun <K: Any, V: Any> Assert<Map.Entry<K, V>>.keyValue(key: K, value: V) {
    property(subject::key).toBe(key)
    property(subject::value).toBe(value)
}
