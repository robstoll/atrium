package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.include

abstract class MapAsEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    asEntriesFunName: String,
    asEntries: Assert<Map<String, Int>>.() -> Assert<Set<Map.Entry<String, Int>>>,
    asEntriesWithCreator: Assert<Map<String, Int>>.(Assert<Set<Map.Entry<String, Int>>>.() -> Unit) -> Assert<Set<Map.Entry<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val asEntriesWithCreatorFun = "$asEntriesFunName with Creator"
    include(object : SubjectLessAssertionSpec<Map<String, Int>>("$describePrefix[$asEntriesFunName] ",
        asEntriesFunName to mapToCreateAssertion { asEntries(this) } ,
        asEntriesWithCreatorFun to mapToCreateAssertion { asEntriesWithCreator(this){ contains("a" to 1)} }
    ) {})

    val holdingSubject: Map<String, Int> = mapOf("a" to 1, "c" to 3, "e" to 5, "g" to 7)
    val failingSubject: Map<String, Int> = mapOf("b" to 2, "d" to 4, "f" to 6, "h" to 8)

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, "$describePrefix[$asEntriesFunName] ",
        checkingTriple(asEntriesFunName, { asEntries(this).contains { isKeyValue("g", 7) } }, holdingSubject, failingSubject),
        checkingTriple(asEntriesWithCreatorFun, { asEntriesWithCreator(this){ contains { isKeyValue("g", 7) } } }, holdingSubject, failingSubject)
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    describeFun(asEntriesFunName) {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(mapOf(1 to "a", 2 to "b")).asEntries().contains.inAnyOrder.only.entries(
                { isKeyValue(2, "b") },
                { key{ isGreaterThan(0)}.and.value.startsWith("a") }
            )
        }
    }
})
