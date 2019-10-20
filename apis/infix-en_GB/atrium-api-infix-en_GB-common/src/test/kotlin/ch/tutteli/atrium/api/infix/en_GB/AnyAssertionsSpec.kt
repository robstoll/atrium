package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.toBe
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.name
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

////TODO remove once you have AnyAssertionsSpec
//object DummyRemove : Spek({
//  describe("dummy"){
//      it("test"){
//      }
//  }
//})

//class AnyAssertionsSpec : ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
//    fun1<Int, Int>(Expect<Int>::toBe).name to Companion::toBe,
//
//    TODO adjust the following lines -> also use `fun1<...>(...).name to Companion:...`
//    we use `fun...` do distinguish between potential overloads but want to see the infix API in action
//
//    fun1<DataClass, DataClass>(Expect<DataClass>::toBe),
//    fun2<Int?, Int?, Nothing?>(Expect<Int?>::toBe, suffix = " nullable").name to Companion::toBeNullableInt,
//    fun2<DataClass?, DataClass?, Nothing?>(
//        Expect<DataClass?>::toBe,
//        suffix = " nullable"
//    ).name to Companion::toBeNullableDataClass,
//    fun1(Expect<Int>::notToBe),
//    fun1(Expect<DataClass>::notToBe),
//    fun1(Expect<Int?>::notToBe, suffix = " nullable"),
//    fun1(Expect<DataClass?>::notToBe, suffix = " nullable"),
//    fun1(Expect<Int>::isSameAs),
//    fun1(Expect<DataClass>::isSameAs),
//    fun1(Expect<Int?>::isSameAs, suffix = " nullable"),
//    fun1(Expect<DataClass?>::isSameAs, suffix = " nullable"),
//    fun1(Expect<Int>::isNotSameAs),
//    fun1(Expect<DataClass>::isNotSameAs),
//    fun1(Expect<Int?>::isNotSameAs, suffix = " nullable"),
//    fun1(Expect<DataClass?>::isNotSameAs, suffix = " nullable"),
//
//    "${Expect<Int?>::toBe.name}(null)" to Companion::toBeNull,
//    fun1(Expect<Int?>::toBeNullIfNullGivenElse),
//    "isA" to Companion::isAFeature,
//    "isA" to Companion::isAStringToInt,
//    "isA" to Companion::isAStringToInt,
//    "isA" to Companion::isAString,
//    "isA" to Companion::isACharSequence,
//    "isA" to Companion::isASubType,
//    "isA" to Companion::isAIntLess,
//    "notToBeNull" to Companion::notToBeNull,
//    Companion::notToBeNullLess,
//    Companion::notToBeNullGreaterAndLess,
//
//    getAndImmediatePair(),
//    getAndLazyPair()
//) {
//
//    companion object {
//
//        private fun toBe(expect: Expect<Int>, a: Int): Expect<Int?> =
//            expect toBe a
//
//        TODO adjust the following to infix API as well
//        private fun toBeNullableInt(expect: Expect<Int?>, a: Int?): Expect<Int?> =
//            expect.toBe(a)
//
//        private fun toBeNullableDataClass(expect: Expect<DataClass?>, a: DataClass?): Expect<DataClass?> =
//            expect.toBe(a)
//
//        private fun toBeNull(expect: Expect<Int?>) = expect.toBe(null)
//
//        private fun isAFeature(expect: Expect<Int?>): Expect<Int> = expect.isA()
//
//        private val andImmediate: KProperty1<Expect<Int>, Expect<Int>> = Expect<Int>::and
//        fun getAndImmediatePair(): Pair<String, Expect<Int>.() -> Expect<Int>> = andImmediate.name to Expect<Int>::and
//
//        private val andLazyName: KFunction2<Expect<Int>, Expect<Int>.() -> Unit, Expect<Int>> = Expect<Int>::and
//        fun getAndLazyPair(): Pair<String, Expect<Int>.(Expect<Int>.() -> Unit) -> Expect<Int>> =
//            andLazyName.name to Expect<Int>::and
//
//        private inline fun <reified TSub : Any> isA(
//            expect: Expect<*>,
//            noinline assertionCreator: Expect<TSub>.() -> Unit
//        ) = expect.isA(assertionCreator)
//
//        //TODO get rid of different overloads as soon as https://youtrack.jetbrains.com/issue/KT-19884 is fixed
//        private fun isAStringToInt(expect: Expect<*>, assertionCreator: Expect<Int>.() -> Unit) =
//            isA(expect, assertionCreator)
//
//        private fun isAString(expect: Expect<*>, assertionCreator: Expect<String>.() -> Unit) =
//            isA(expect, assertionCreator)
//
//        private fun isACharSequence(expect: Expect<*>, assertionCreator: Expect<CharSequence>.() -> Unit) =
//            isA(expect, assertionCreator)
//
//        private fun isASubType(expect: Expect<*>, assertionCreator: Expect<SubType>.() -> Unit) =
//            isA(expect, assertionCreator)
//
//        private fun isAIntLess(expect: Expect<Number>, number: Int) = expect.isA<Int> { isLessThan(number) }
//
//        private fun notToBeNull(expect: Expect<Int?>, assertionCreator: Expect<Int>.() -> Unit) =
//            expect.notToBeNull(assertionCreator)
//
//        private fun notToBeNullLess(expect: Expect<Int?>, number: Int) =
//            expect.notToBeNull { isLessThan(number) }
//
//        private fun notToBeNullGreaterAndLess(expect: Expect<Int?>, lowerBound: Int, upperBound: Int) =
//            expect.notToBeNull {
//                isGreaterThan(lowerBound)
//                isLessThan(upperBound)
//            }
//    }
//}
