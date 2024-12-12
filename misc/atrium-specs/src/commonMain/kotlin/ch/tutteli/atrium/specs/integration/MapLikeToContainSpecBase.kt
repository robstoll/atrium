package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof
import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.Fun3
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.fun3
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import kotlin.reflect.KFunction3
import kotlin.reflect.KFunction4

typealias MFun2<K, V, T> = Fun2<Map<out K, V>, Pair<K, T>, Array<out Pair<K, T>>>
typealias MFun3<K, V, T, A3> = Fun3<Map<out K, V>, Pair<K, T>, Array<out Pair<K, T>>, A3>

fun <K, V, T> mfun2(
    f: KFunction3<Expect<Map<out K, V>>, Pair<K, T>, Array<out Pair<K, T>>, Expect<Map<out K, V>>>
) = fun2(f)

fun <K, V, T, A3> mfun3(
    f: KFunction4<Expect<Map<out K, V>>, Pair<K, T>, Array<out Pair<K, T>>, A3, Expect<Map<out K, V>>>
) = fun3(f)


abstract class MapLikeToContainSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val keyDoesNotExist = DescriptionMapLikeProof.KEY_DOES_NOT_EXIST.string
        val toBeLessThanDescr = DescriptionComparableProof.TO_BE_LESS_THAN.string

        val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)
        val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)
        val emptyMap: Map<out String, Int> = mapOf()

        fun entry(key: String?) = DescriptionMapLikeProof.ENTRY_WITH_KEY.string.format(if(key == null) "null" else "\"$key\"")
        fun entry(key: String?, value: Any): String = entry(key) + ": " + value
    }
}
