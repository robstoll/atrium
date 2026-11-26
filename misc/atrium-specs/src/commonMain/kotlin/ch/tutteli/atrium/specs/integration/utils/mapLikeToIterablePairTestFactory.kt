package ch.tutteli.atrium.specs.integration.utils

import ch.tutteli.atrium.api.fluent.en_GB.cause
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.emptyIterableLikeTypes
import ch.tutteli.atrium.specs.iterableLikeTypesWithElement1
import ch.tutteli.kbox.glue

fun <SubjectT> mapLikeToIterablePairTestFactory(
    subject: SubjectT,
    expectationFun: SpecPair<Expect<SubjectT>.(MapLike) -> Expect<SubjectT>>,
    vararg otherExpectationFuns: SpecPair<Expect<SubjectT>.(MapLike) -> Expect<SubjectT>>,
) = testFactory {
    (expectationFun glue otherExpectationFuns).forEach { (name, expectationFunAcceptingMapLike) ->
        describe(name) {
            (mapOf("Map" to mapOf<String, Int>()) + emptyIterableLikeTypes).forEach { (type, input) ->
                it("passing an empty $type throws an IllegalArgumentException") {
                    expect {
                        expect(subject).expectationFunAcceptingMapLike(input)
                    }.toThrow<IllegalArgumentException> {
                        messageToContain("MapLike without entries are not allowed")
                    }
                }
            }

            iterableLikeTypesWithElement1.forEach { (type, input) ->
                it("passing $type with a single element 1 throws an IllegalArgumentException (since not a Pair)") {
                    expect {
                        expect(subject).expectationFunAcceptingMapLike(input)
                    }.toThrow<IllegalArgumentException> {
                        messageToContain(
                            "MapLikeToIterablePairTransformer accepts arguments of types:",
                            "Map as well as IterableLike with an element type `Pair<*, *>` and `Map.Entry<*, *>`"
                        )
                        cause<IllegalArgumentException> {
                            messageToContain("element of Iterable is neither a Pair nor a Map.Entry")
                        }
                    }
                }
            }

            it("passing a String instead of a MapLike throws an IllegalArgumentException") {
                expect {
                    expect(subject).expectationFunAcceptingMapLike("test")
                }.toThrow<IllegalArgumentException> {
                    messageToContain(
                        "MapLikeToIterablePairTransformer accepts arguments of types:",
                        "Map as well as IterableLike with an element type `Pair<*, *>` and `Map.Entry<*, *>`",
                        "given:",
                        "String"
                    )
                    cause<IllegalArgumentException> {
                        messageToContain("IterableLikeToIterableTransformer accepts arguments of types:")
                    }
                }
            }
        }
    }
}
