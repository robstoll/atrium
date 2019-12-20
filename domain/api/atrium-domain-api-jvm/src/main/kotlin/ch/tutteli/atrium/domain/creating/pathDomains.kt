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
 * scoping it to the domain of subjects which have a type extending [Path].
 *
 * @since 0.9.0
 */
val <T : Path> Expect<T>._domain: PathDomain<T>
    get() = PathDomainImpl(
        PathOnlyDomainImpl(this),
        ComparableOnlyDomainImpl(this),
        IterableDomainImpl(IterableOnlyDomainImpl(this), AnyDomainImpl(this))
    )

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Path],
 * which an implementation of the domain of Atrium has to provide.
 *
 * @since 0.9.0
 */
interface PathDomain<T : Path> : PathOnlyDomain<T>, ComparableOnlyDomain<Path, T>, IterableDomain<Path, T>


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Path]
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 *
 * @since 0.9.0
 */
interface PathOnlyDomain<T : Path> : ExpectDomain<T> {

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

