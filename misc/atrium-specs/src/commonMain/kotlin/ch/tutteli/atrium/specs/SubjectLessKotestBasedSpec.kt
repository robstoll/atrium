import ch.tutteli.atrium.api.fluent.en_GB.its
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject

//TODO 1.1.0 using kotest causes that js-tests are no longer run (neither via Intelij nor via gradle)
// I have something in mind that it is caused by a kotlin bug which popped up in Kotlin 1.4 and was eventually fixed in
// kotlin 1.6 -- so check in 1.1.0 if it still occurs if we increase the kotlin version

//package ch.tutteli.atrium.specs
//
//import ch.tutteli.atrium.api.fluent.en_GB.feature
//import ch.tutteli.atrium.api.fluent.en_GB.toEqual
//import ch.tutteli.atrium.api.fluent.en_GB.toHaveElementsAndAll
//import ch.tutteli.atrium.api.verbs.internal.expect
//import ch.tutteli.atrium.assertions.Assertion
//import ch.tutteli.atrium.assertions.AssertionGroup
//import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
//import ch.tutteli.atrium.assertions.builders.assertionBuilder
//import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
//import ch.tutteli.atrium.core.None
//import ch.tutteli.atrium.creating.CollectingExpect
//import ch.tutteli.atrium.creating.Expect
//import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
//import ch.tutteli.atrium.logic._logic
//import ch.tutteli.atrium.logic.creating.RootExpectBuilder
//import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
//import ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster
//import io.kotest.core.spec.style.funSpec
//
//fun <T> subjectLessKotestBasedSpec(
//    groupPrefix: String,
//    vararg assertionCreator: Pair<String, Expect<T>.() -> Unit>
//) = funSpec {
//    assertionCreator.forEach { (name, createAssertion) ->
//        test("${groupPrefix}fun `$name`: assertion function can be used in an ${AssertionGroup::class.simpleName} with an ${ExplanatoryAssertionGroupType::class.simpleName} and report without failure") {
//            @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
//            @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
//            val assertions = CollectingExpect<T>(None, expect(1)._logic.components)
//                .appendAsGroup(createAssertion)
//                .getAssertions()
//
//            expandAssertionGroups(assertions)
//
//
//            @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
//            @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
//            val expect = RootExpectBuilder.forSubject(1.0)
//                .withVerb("custom assertion verb")
//                .withOptions {
//                    withComponent(AtriumErrorAdjuster::class) { _ -> NoOpAtriumErrorAdjuster }
//                }
//                .build()
//
//            val explanatoryGroup = assertionBuilder.explanatoryGroup
//                .withDefaultType
//                .withAssertions(assertions)
//                .build()
//            expect._logic.append(explanatoryGroup)
//        }
//
//        test(" ${groupPrefix}fun `$name`: expectation function does not hold if there is no subject"){
//            @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
//            @UseExperimental(ExperimentalComponentFactoryContainer::class)
//            val assertions = CollectingExpect<T>(None, expect(1)._logic.components)
//                .appendAsGroup(createAssertion)
//                .getAssertions()
//            expect(assertions).toHaveElementsAndAll {
//                feature(Assertion::holds).toEqual(false)
//            }
//        }
//    }
//}
//
///**
// * Calls recursively [AssertionGroup.assertions] on every expectation-group contained in [assertions].
// */
//private tailrec fun expandAssertionGroups(assertions: List<Assertion>) {
//    if (assertions.isEmpty()) return
//
//    expandAssertionGroups(
//        assertions
//            .asSequence()
//            .filterIsInstance<AssertionGroup>()
//            .flatMap { it.assertions.asSequence() }
//            .toList()
//    )
//}
