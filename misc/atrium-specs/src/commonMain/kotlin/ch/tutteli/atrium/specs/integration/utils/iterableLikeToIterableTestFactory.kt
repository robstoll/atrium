package ch.tutteli.atrium.specs.integration.utils

import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.specs.emptyIterableLikeTypes
import kotlin.collections.component1
import kotlin.collections.component2

fun <SubjectT> iterableLikeToIterableTestFactory(
    description: String,
    subject: SubjectT,
    funIterableLike: Expect<SubjectT>.(IterableLike) -> Expect<SubjectT>,
    describePrefix: String = "[Atrium] "
) = testFactory {
    describe(describePrefix + description) {
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
                "Iterable<*>, Sequence<*>, Array<*>, CharArray, ByteArray, ShortArray, IntArray, LongArray, FloatArray, DoubleArray and BooleanArray",
                "given:",
                "String")
            }
        }
    }
}
