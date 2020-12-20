package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import kotlin.reflect.KFunction3

typealias MFun2<K, V, T> = Fun2<Map<out K, V>, Pair<K, T>, Array<out Pair<K, T>>>

fun <K, V, T> mfun2(
    f: KFunction3<Expect<Map<out K, V>>, Pair<K, T>, Array<out Pair<K, T>>, Expect<Map<out K, V>>>
) = fun2(f)

abstract class MapLikeContainsSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()
        val lessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()

        val separator = lineSeperator

        val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)
        val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)
        val emptyMap: Map<out String, Int> = mapOf()

        fun entry(key: String?) = DescriptionMapAssertion.ENTRY_WITH_KEY.getDefault().format(if(key == null) "null" else "\"$key\"")
        fun entry(key: String?, value: Any): String = entry(key) + ": " + value
    }
}
