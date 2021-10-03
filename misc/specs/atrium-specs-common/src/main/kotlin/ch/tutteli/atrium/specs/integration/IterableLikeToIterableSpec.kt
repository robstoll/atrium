package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.specs.emptyIterableLikeTypes
import ch.tutteli.atrium.specs.iterableLikeTypesWithElement1
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class IterableLikeToIterableSpec<T>(
    description: String,
    subject: T,
    funIterableLike: Expect<T>.(IterableLike) -> Expect<T>,
    describePrefix: String = "[Atrium] "
) : Spek({

    describe(describePrefix + description) {
        context("context: expecting an IterableLike") {
            emptyIterableLikeTypes.forEach { (type, input) ->
                it("passing an empty $type throws an IllegalArgumentException") {
                    expect {
                        expect(subject).funIterableLike(input)
                    }.toThrow<IllegalArgumentException> {
                        messageToContain("IterableLike without elements are not allowed")
                    }
                }
            }

            it("passing a String instead of an IterableLike throws an IllegalArgumentException") {
                expect {
                    expect(subject).funIterableLike("test")
                }.toThrow<IllegalArgumentException> {
                    messageToContain(
                        "IterableLikeToIterableTransformer accepts arguments of types:",
                        "Iterable, Sequence, Array, CharArray, ByteArray, ShortArray, IntArray, LongArray, FloatArray, DoubleArray and BooleanArray"
                    )
                }
            }
        }
    }
})
