@file:Suppress(
    "ObjectPropertyName",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.impl.*
import java.nio.file.Path

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Path];
 * i.e. it returns a [PathDomain] for this [Expect].
 *
 * @since 0.9.0
 */
val <T : Path> Expect<T>._domain: PathDomain<T>
    get() = PathDomainImpl(
        PathSubDomainImpl(this),
        ComparableSubDomainImpl(this),
        IterableDomainImpl(IterableSubDomainImpl(this), AnyDomainImpl(this))
    )

/**
 * Represents the [ExpectDomain] whose type extends [Path];
 * i.e. the subject of the underlying [expect] has such a type.
 *
 * @since 0.9.0
 */
interface PathDomain<T : Path> : PathSubDomain<T>, ComparableSubDomain<Path, T>, IterableDomain<Path, T>


/**
 * Represents a sub-[ExpectDomain] whose type extends [Path]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Path] (e.g. the functions of the [AnyDomain]).
 *
 * @since 0.9.0
 */
interface PathSubDomain<T : Path> : ExpectDomain<T> {

    val fileName: ExtractedFeaturePostStep<T, String>
    val extension: ExtractedFeaturePostStep<T, String>
    val fileNameWithoutExtension: ExtractedFeaturePostStep<T, String>
    val parent: ExtractedFeaturePostStep<T, Path>

    fun exists(): Assertion
    fun existsNot(): Assertion

    fun isReadable(): Assertion
    fun isWritable(): Assertion
    fun isRegularFile(): Assertion
    fun isDirectory(): Assertion

    fun startsWith(expected: Path): Assertion
    fun startsNotWith(expected: Path): Assertion
    fun endsWith(expected: Path): Assertion
    fun endsNotWith(expected: Path): Assertion
}

