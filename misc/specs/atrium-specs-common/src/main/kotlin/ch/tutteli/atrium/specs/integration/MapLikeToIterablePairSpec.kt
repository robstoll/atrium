package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.emptyIterableLikeTypes
import ch.tutteli.atrium.specs.iterableLikeTypesWithElement1
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class MapLikeToIterablePairSpec<T>(
    description: String,
    subject: T,
    funMapLike: Expect<T>.(MapLike) -> Expect<T>,
    describePrefix: String = "[Atrium] "
) : Spek({

    describe(describePrefix + description) {
        context("context: expecting an MapLike") {
            (mapOf("Map" to mapOf<String, Int>()) + emptyIterableLikeTypes).forEach { (type, input) ->
                it("passing an empty $type throws an IllegalArgumentException") {
                    expect {
                        expect(subject).funMapLike(input)
                    }.toThrow<IllegalArgumentException> {
                        messageToContain("MapLike without entries are not allowed")
                    }
                }
            }
            iterableLikeTypesWithElement1.forEach { (type, input) ->
                it("passing $type with a single element 1 throws an IllegalArgumentException (since not a Pair)") {
                    expect {
                        expect(subject).funMapLike(input)
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
                    expect(subject).funMapLike("test")
                }.toThrow<IllegalArgumentException> {
                    messageToContain(
                        "MapLikeToIterablePairTransformer accepts arguments of types:",
                        "Map as well as IterableLike with an element type `Pair<*, *>` and `Map.Entry<*, *>`"
                    )
                    cause<IllegalArgumentException> {
                        messageToContain("IterableLikeToIterableTransformer accepts arguments of types:")
                    }
                }
            }
        }
    }
})
