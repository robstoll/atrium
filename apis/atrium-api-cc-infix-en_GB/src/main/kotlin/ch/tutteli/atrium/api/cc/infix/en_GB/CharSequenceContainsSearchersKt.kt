package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("Do not use this class, it is only here to retain binary compatibility (file was renamed to charSequenceContainsCreators), will be removed with 1.0.0")
object CharSequenceContainsSearchersKt {

    @JvmStatic
    @Deprecated(
        "use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder value expected")
    )
    fun <T : CharSequence> value(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        expected: Any
    ): AssertionPlant<T> = the(checkerBuilder, Values(expected))

    @JvmStatic
    @Deprecated(
        "use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder the values")
    )
    fun <T : CharSequence> the(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        values: Values<Any>
    ): AssertionPlant<T> = checkerBuilder the values


    @JvmStatic
    @Deprecated(
        "use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder value expected")
    )
    fun <T : CharSequence> valueIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        expected: Any
    ): AssertionPlant<T> = valuesIgnoringCase(checkerBuilder, Values(expected))

    @JvmStatic
    @Deprecated(
        "use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder the values")
    )
    fun <T : CharSequence> valuesIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        values: Values<Any>
    ): AssertionPlant<T> = checkerBuilder the values


    @JvmStatic
    @Deprecated(
        "use the extension fun `defaultTranslationOf` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder defaultTranslationOf translatable")
    )
    fun <T : CharSequence> defaultTranslationOf(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        translatable: Translatable
    ): AssertionPlant<T> = the(checkerBuilder, DefaultTranslationsOf(translatable))

    @JvmStatic
    @Deprecated(
        "use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder the translatables")
    )
    fun <T : CharSequence> the(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        translatables: DefaultTranslationsOf
    ): AssertionPlant<T> = checkerBuilder the translatables


    @JvmStatic
    @Deprecated(
        "use the extension fun `defaultTranslationOf` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder defaultTranslationOf translatable")
    )
    fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        translatable: Translatable
    ): AssertionPlant<T> = valuesIgnoringCase(checkerBuilder, DefaultTranslationsOf(translatable))

    @JvmStatic
    @Deprecated(
        "use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder the translatables")
    )
    fun <T : CharSequence> valuesIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        translatables: DefaultTranslationsOf
    ): AssertionPlant<T> = checkerBuilder the translatables


    @JvmStatic
    @Deprecated(
        "use the extension fun `regex` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder regex pattern")
    )
    fun <T : CharSequence> regex(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        pattern: String
    ): AssertionPlant<T> = the(checkerBuilder, RegexPatterns(pattern))

    @JvmStatic
    @Deprecated(
        "use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder the patterns")
    )
    fun <T : CharSequence> the(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        patterns: RegexPatterns
    ): AssertionPlant<T> = checkerBuilder the patterns


    @JvmStatic
    @Deprecated(
        "use the extension fun `regex` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder regex pattern")
    )
    fun <T : CharSequence> regexIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        pattern: String
    ): AssertionPlant<T> = regexIgnoringCase(checkerBuilder, RegexPatterns(pattern))

    @JvmStatic
    @Deprecated(
        "use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder the patterns")
    )
    fun <T : CharSequence> regexIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        patterns: RegexPatterns
    ): AssertionPlant<T> = checkerBuilder the patterns
}
