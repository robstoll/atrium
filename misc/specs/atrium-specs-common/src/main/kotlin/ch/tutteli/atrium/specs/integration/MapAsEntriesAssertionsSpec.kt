package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.isKeyValue
import ch.tutteli.atrium.api.cc.en_GB.key
import ch.tutteli.atrium.api.cc.en_GB.value
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapAsEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    asEntriesFeature: Feature0<Map<String, Int>, Set<Map.Entry<String, Int>>>,
    asEntries: Fun1<Map<String, Int>, Expect<Set<Map.Entry<String, Int>>>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Map<String, Int>>(
        describePrefix,
        asEntriesFeature.forSubjectLess().adjustName { "$it feature" },
        asEntries.forSubjectLess { contains("a" to 1) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    describeFun(asEntriesFeature.name + " feature") {
        it("transformation can be applied and an assertion made") {
            verbs.check(mapOf("a" to 1, "b" to 2)).(asEntriesFeature.lambda)().contains.inAnyOrder.only.entries(
                { asAssert().isKeyValue("b", 2) },
                { asAssert().key { asExpect().startsWith("a") }.asExpect().and.asAssert().value.asExpect().isGreaterOrEquals(1) }
            )
        }
    }

    describeFun(asEntries.name) {
        it("transformation can be applied and an assertion made") {
            verbs.check(mapOf("a" to 1, "b" to 2)).(asEntries.lambda){
                contains.inAnyOrder.only.entries(
                    { asAssert().isKeyValue("b", 2) },
                    {
                        asAssert().key { asExpect().startsWith("a") }
                        asAssert().value.asExpect().isGreaterOrEquals(1)
                    }
                )
            }
        }
    }
})
