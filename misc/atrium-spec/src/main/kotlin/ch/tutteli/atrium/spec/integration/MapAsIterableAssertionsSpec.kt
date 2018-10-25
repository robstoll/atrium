package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import org.jetbrains.spek.api.include

abstract class MapAsIterableAssertionsSpec(
    verbs: AssertionVerbFactory,
    asKeysFunName: String,
    keys: Assert<Map<String, Int>>.() -> Assert<Iterable<String>>,
    asValuesFunName: String,
    values: Assert<Map<String, Int>>.() -> Assert<Iterable<Int>>,
    asEntriesFunName: String,
    entries: Assert<Map<String, Int>>.() -> Assert<Iterable<Map.Entry<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Map<String, Int>>("$describePrefix[keys] ",
        asKeysFunName to mapToCreateAssertion { keys(this) }
    ) {})

    include(object : SubjectLessAssertionSpec<Map<String, Int>>("$describePrefix[values] ",
        asValuesFunName to mapToCreateAssertion { values(this) }
    ) {})

    include(object : SubjectLessAssertionSpec<Map<String, Int>>("$describePrefix[entries] ",
        asEntriesFunName to mapToCreateAssertion { entries(this) }
    ) {})

    val holdingSubject: Map<String, Int> = mapOf("a" to 1, "c" to 3, "e" to 5, "g" to 7)
    val failingSubject: Map<String, Int> = mapOf("b" to 2, "d" to 4, "f" to 6, "h" to 8)

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, "$describePrefix[keys] ",
        checkingTriple("$asKeysFunName keys", { keys(this).contains("e") }, holdingSubject, failingSubject)
    ) {})

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, "$describePrefix[values] ",
        checkingTriple("$asValuesFunName values", { values(this).contains(3) }, holdingSubject, failingSubject)
    ) {})

//    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, "$describePrefix[entries] ",
//        checkingTriple("$asEntriesFunName entries", { entries(this).contains("g" to 7) }, holdingSubject, failngSubject)
//    ) {})
})
