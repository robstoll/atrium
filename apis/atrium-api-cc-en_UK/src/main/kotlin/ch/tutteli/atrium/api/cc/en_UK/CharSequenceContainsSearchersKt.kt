package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("Do not use this class, it is only here to retain binary compatibility (file was renamed to charSequenceContainsCreators), will be removed with 1.0.0")
object CharSequenceContainsSearchersKt {

    @JvmStatic
    fun <T : CharSequence> value(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Any): AssertionPlant<T>
        = values(checker, expected)

    @JvmStatic
    fun <T : CharSequence> values(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Any, vararg otherExpected: Any): AssertionPlant<T>
        = checker.values(expected, *otherExpected)


    @JvmStatic
    @JvmName("valueIgnoringCase")
    fun <T : CharSequence> value(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Any): AssertionPlant<T>
        = values(checker, expected)

    @JvmStatic
    @JvmName("valuesIgnoringCase")
    fun <T : CharSequence> values(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Any, vararg otherExpected: Any): AssertionPlant<T>
        = checker.values(expected, *otherExpected)


    @JvmStatic
    fun <T : CharSequence> defaultTranslationOf(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
        = checker.defaultTranslationOf(expected, *otherExpected)

    @JvmStatic
    @JvmName("defaultTranslationOfIgnoringCase")
    fun <T : CharSequence> defaultTranslationOf(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
        = checker.defaultTranslationOf(expected, *otherExpected)


    @JvmStatic
    fun <T : CharSequence> regex(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, pattern: String, vararg otherPatterns: String): AssertionPlant<T>
        = checker.regex(pattern, *otherPatterns)

    @JvmStatic
    @JvmName("regexIgnoringCase")
    fun <T : CharSequence> regex(checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, pattern: String, vararg otherPatterns: String): AssertionPlant<T>
        = checker.regex(pattern, *otherPatterns)
}
