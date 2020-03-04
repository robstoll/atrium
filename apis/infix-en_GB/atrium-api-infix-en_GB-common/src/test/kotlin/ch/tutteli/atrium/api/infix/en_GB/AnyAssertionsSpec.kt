package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AnyAssertionsSpec
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction2

class AnyAssertionsSpec : ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
    fun1<Int, Int>(Expect<Int>::toBe),
    fun1<DataClass, DataClass>(Expect<DataClass>::toBe),
    fun1<Int?, Int?>(Expect<Int?>::toBe),
    fun1<DataClass?, DataClass?>(Expect<DataClass?>::toBe),
    fun1(Expect<Int>::notToBe),
    fun1(Expect<DataClass>::notToBe),
    fun1(Expect<Int?>::notToBe).withNullableSuffix(),
    fun1(Expect<DataClass?>::notToBe).withNullableSuffix(),
    fun1(Expect<Int>::isSameAs),
    fun1(Expect<DataClass>::isSameAs),
    fun1(Expect<Int?>::isSameAs).withNullableSuffix(),
    fun1(Expect<DataClass?>::isSameAs).withNullableSuffix(),
    fun1(Expect<Int>::isNotSameAs),
    fun1(Expect<DataClass>::isNotSameAs),
    fun1(Expect<Int?>::isNotSameAs).withNullableSuffix(),
    fun1(Expect<DataClass?>::isNotSameAs).withNullableSuffix(),

    "${Expect<Int?>::toBe.name}(null)" to ::toBeNull,
    fun1(Expect<Int?>::toBeNullIfNullGivenElse),
    "isA" to ::isAFeature,
    "isA" to ::isAStringToInt,
    "isA" to ::isAStringToInt,
    "isA" to ::isAString,
    "isA" to ::isACharSequence,
    "isA" to ::isASubType,
    "isA" to ::isAIntLess,
    "notToBeNull" to ::notToBeNull,
    ::notToBeNullLess,
    ::notToBeNullGreaterAndLess,

    getAndImmediatePair(),
    getAndLazyPair()
) {

    companion object : WithAsciiReporter()

    @Suppress("unused")
    fun ambiguityTest() {
        val a1: Expect<Number> = notImplemented()
        val a1b: Expect<Number?> = notImplemented()

        a1 toBe 1
        a1 toBe 1.2
        a1 notToBe 1
        a1 notToBe 1.2
        a1 isSameAs 1
        a1 isSameAs 1.2
        a1 isNotSameAs 1
        a1 isNotSameAs 1.2
        a1.isA<Int>()
        a1.isA<Int> {}

        a1b toBe 1
        a1b toBe 1.2
        a1b notToBe 1
        a1b notToBe 1.2
        a1b isSameAs 1
        a1b isSameAs 1.2
        a1b isNotSameAs 1
        a1b isNotSameAs 1.2
        a1b.isA<Int>()
        a1b.isA<Int> {}

        a1b notToBeNull o toBe 1
        a1b notToBeNull {}

        a1 and o toBe 1
        a1 and { o toBe 1 }
    }

    //regression for #298, should compile without the need for E : Any or List<E?>
    @Suppress("unused")
    fun <E> Expect<List<E>>.firstIs(value: E) = o get index(0) { o toBe value }
}

private fun toBeNull(expect: Expect<Int?>) = expect toBe null

@Suppress("RemoveExplicitTypeArguments")
private fun isAFeature(expect: Expect<Int?>): Expect<Int> = expect.isA<Int>()

private fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> =
    "non existing in infix" to { e: Expect<Int> -> e }

private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
private fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
    andLazyName.name to Expect<Int>::and

private inline fun <reified TSub : Any> isA(
    expect: Expect<*>,
    noinline assertionCreator: Expect<TSub>.() -> Unit
) = expect.isA(assertionCreator)

//TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
private fun isAStringToInt(expect: Expect<*>, assertionCreator: Expect<Int>.() -> Unit) =
    isA(expect, assertionCreator)

private fun isAString(expect: Expect<*>, assertionCreator: Expect<String>.() -> Unit) =
    isA(expect, assertionCreator)

private fun isACharSequence(expect: Expect<*>, assertionCreator: Expect<CharSequence>.() -> Unit) =
    isA(expect, assertionCreator)

private fun isASubType(expect: Expect<*>, assertionCreator: Expect<AnyAssertionsSpec.SubType>.() -> Unit) =
    isA(expect, assertionCreator)

private fun isAIntLess(expect: Expect<Number>, number: Int) =
    expect.isA<Int> { o isLessThan number }

private fun notToBeNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
    expect notToBeNull assertionCreator

private fun notToBeNullLess(expect: Expect<Int?>, number: Int) =
    expect.notToBeNull { isLessThan(number) }

private fun notToBeNullGreaterAndLess(expect: Expect<Int?>, lowerBound: Int, upperBound: Int) =
    expect.notToBeNull {
        o isGreaterThan lowerBound
        o isLessThan upperBound
    }
