@file:Suppress("ClassName")

package ch.tutteli.atrium.api.infix.en_GB

/**
 * Marker interface for keywords.
 *
 * For instance, can be used to add a deprecated overload in case an expectation function accepts Any as argument type
 * (see toEqual).
 */
interface Keyword

/**
 * A helper construct to allow expressing expectations about a path being a regular file.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeARegularFile
 */
object aRegularFile : Keyword

/**
 * A helper construct to allow expressing expectations about a path being a directory.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeADirectory
 */
object aDirectory : Keyword

/**
 * A helper construct to allow expressing expectations about a path being an empty directory.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeAnEmptyDirectory
 */
object anEmptyDirectory : Keyword

/**
 * A helper construct to allow expressing expectations about a path being a symbolic link.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeASymbolicLink
 */
object aSymbolicLink : Keyword

/**
 * A helper construct to allow expressing expectations about a path being absolute.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeAbsolute
 *
 * @since 0.14.0
 */
object absolute : Keyword

/**
 * A helper construct to allow expressing expectations about a path being relative.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeRelative
 *
 * @since 0.14.0
 */
object relative : Keyword

/**
 * Represents a helper construct which allows expressing blankness.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToBeBlank
 */
object blank : Keyword

/**
 * Represents the pseudo keyword `case` as in [ignoring] `case`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object case : Keyword

/**
 * Represents a helper construct which allows expressing emptiness.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toBeEmpty
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CollectionExpectationSamples.notToBeEmpty
 */
object empty : Keyword

/**
 * Represents the pseudo keyword `entries` as in [grouped] `entries`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object entries : Keyword

/**
 * A helper construct to allow expressing expectations about path existence.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.notToExist
 */
object existing : Keyword

/**
 * Represents the pseudo keyword `group` as in [within] `group`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object group : Keyword

/**
 * Represents the pseudo keyword `next` as in [toHave] `next`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IteratorExpectationSamples.toHave_next
 *
 * @since 0.12.0
 */
object next : Keyword

/**
 * Represents a filler, a pseudo keyword where there isn't really a good keyword.
 * A reader should skip this filler without reading it. For instance, `contains o atLeast 1...` should be read as
 * `contains at least once...`
 *
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.andFeature
 *
 * @since 0.12.0
 */
object o : Keyword

/**
 * Represents the pseudo keyword `only` as in [and] `only`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainBuilder
 */
object only : Keyword

/**
 * Represents the pseudo keyword `order` as in [inAny] `order`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainBuilder
 */
object order : Keyword

/**
 * Represents a helper construct which allows expressing presence.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object present : Keyword


/**
 * A helper construct to allow expressing expectations about a path being readable.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeReadable
 */
object readable : Keyword

/**
 * Represents the pseudo keyword `success` as in [toEqual] `success`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use aSuccess; will be removed with 1.0.0 at the latest",
    ReplaceWith("aSuccess", "ch.tutteli.atrium.api.infix.en_GB.aSuccess")
)
object success : Keyword

/**
 * A helper construct to allow expressing expectations about a something being a success.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @since 0.17.0
 */
object aSuccess : Keyword

/**
 * A helper construct to allow expressing expectations about a path being writable.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeWritable
 */
object writable : Keyword

/**
 * A helper construct to allow expressing expectations about a path being executable.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.toBeExecutable
 */
object executable : Keyword

/**
 * A helper construct to allow expressing expectations about iterable contains no duplicates.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toHaveElementsAndNoDuplicates
 *
 * @since 0.14.0
 */
object noDuplicates : Keyword

/**
 * A helper construct to allow expressing expectations about elements as in `expect(iterable) toHave elements`.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.notToHaveElements
 *
 * @since 0.17.0
 */
object elements : Keyword
