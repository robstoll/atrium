//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.specs.defaultBulletPoints
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KClass

@OptIn(ExperimentalWithOptions::class)
@ExperimentalComponentFactoryContainer
class BulletPointProviderSpec : Spek({

    fun <T> expectWitNewBulletPoint(newBulletPoint: Pair<KClass<out BulletPointIdentifier>, String>, t: T) =
        expect(t).withOptions {
            withComponent(BulletPointProvider::class) { _ ->
                object : BulletPointProvider {
                    override fun getBulletPoints(): Map<KClass<out BulletPointIdentifier>, String> =
                        mapOf(newBulletPoint)
                }
            }
        }

    describe("specifying a provider allows to override bullet points") {
        val redefinedBulletPoints =
            mapOf<KClass<out BulletPointIdentifier>, Pair<String, (Pair<KClass<out BulletPointIdentifier>, String>) -> Expect<*>>>(
                RootAssertionGroupType::class to ("* " to { p ->
                    expectWitNewBulletPoint(p, "a") toEqual "b"
                }),
                ListAssertionGroupType::class to ("- " to { p ->
                    expectWitNewBulletPoint(p, "a")._logic.appendAsGroup {
                        _logic.append(
                            assertionBuilder.list
                                .withDescriptionAndEmptyRepresentation("hello")
                                .withAssertion(_logic.toBe("b")).build()
                        )
                    }
                }),
                FeatureAssertionGroupType::class to (">> " to { p ->
                    expectWitNewBulletPoint(p, "a") feature { f("m", it.length) } toEqual 2
                }),
                PrefixFeatureAssertionGroupHeader::class to ("=> " to { p ->
                    expectWitNewBulletPoint(p, "a") feature { f("m", it.length) } toEqual 2
                }),
                PrefixSuccessfulSummaryAssertion::class to ("(/) " to { p ->
                    expectWitNewBulletPoint(p, listOf(1)) toContainExactly values(1, 2)
                }),
                PrefixFailingSummaryAssertion::class to ("(x) " to { p ->
                    expectWitNewBulletPoint(p, listOf(1)) toContainExactly 2
                }),
                ExplanatoryAssertionGroupType::class to (">> " to { p ->
                    expectWitNewBulletPoint(p, "a")._logic.appendAsGroup {
                        _logic.append(
                            assertionBuilder.explanatoryGroup
                                .withDefaultType
                                .withAssertion(_logic.toBe("b"))
                                .failing
                                .build()
                        )
                    }
                }),
                WarningAssertionGroupType::class to ("(!) " to { p ->
                    expectWitNewBulletPoint(p, "a")._logic.appendAsGroup {
                        _logic.append(
                            assertionBuilder.explanatoryGroup
                                .withWarningType
                                .withAssertion(_logic.toBe("b"))
                                .failing
                                .build()
                        )
                    }
                }),
                InformationAssertionGroupType::class to ("(i) " to { p ->
                    expectWitNewBulletPoint(p, "a")._logic.appendAsGroup {
                        _logic.append(
                            assertionBuilder.explanatoryGroup
                                .withInformationType(withIndent = false)
                                .withAssertion(_logic.toBe("b"))
                                .failing
                                .build()
                        )
                    }
                }),
                HintAssertionGroupType::class to ("(h) " to { p ->
                    expectWitNewBulletPoint(p, "a")._logic.appendAsGroup {
                        _logic.append(
                            assertionBuilder.explanatoryGroup
                                .withHintType
                                .withAssertion(_logic.toBe("b"))
                                .failing
                                .build()
                        )
                    }
                }),
                GroupingAssertionGroupType::class to ("== " to { p ->
                    expectWitNewBulletPoint(p, listOf(1)).group("a group") {
                        toContain(2)
                    }
                }),
            )

        defaultBulletPoints.map { (kClass, defaultBulletPoint) ->
            it("redefining ${kClass.simpleName}") {
                val (newBulletPoint, expectBuilder) = redefinedBulletPoints.getOrElse(kClass) {
                    throw AssertionError("case not covered")
                }
                expect {
                    expectBuilder(kClass to newBulletPoint)
                }.toThrow<AssertionError> {
                    message {
                        toContain(newBulletPoint)
                        notToContain(defaultBulletPoint)
                    }
                }
            }
        }
    }
})
