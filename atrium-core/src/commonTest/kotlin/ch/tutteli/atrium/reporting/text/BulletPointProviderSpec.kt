package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.specs.defaultBulletPoints
import kotlin.reflect.KClass
import io.kotest.core.spec.style.DescribeSpec

@OptIn(ExperimentalWithOptions::class)
@ExperimentalComponentFactoryContainer
class BulletPointProviderSpec : DescribeSpec({

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
                PrefixFeatureAssertionGroupHeader::class to ("=> "  to { p ->
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
                                .withInformationType(false)
                                .withAssertion(_logic.toBe("b"))
                                .failing
                                .build()
                        )
                    }
                })
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
