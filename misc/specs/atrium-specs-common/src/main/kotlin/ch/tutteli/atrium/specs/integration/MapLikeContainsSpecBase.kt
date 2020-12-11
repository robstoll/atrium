package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

abstract class MapLikeContainsSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()
        val lessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()

        val separator = lineSeperator

        fun entry(key: String?) = DescriptionMapAssertion.ENTRY_WITH_KEY.getDefault().format(if(key == null) "null" else "\"$key\"")
        fun entry(key: String, value: Any): String = entry(key) + ": " + value
    }
}
