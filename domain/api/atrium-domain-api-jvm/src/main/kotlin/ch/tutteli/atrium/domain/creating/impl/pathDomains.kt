@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.nio.file.Path

internal class PathDomainImpl<T : Path>(
    pathDomainOnlyImpl: PathOnlyDomain<T>,
    comparableDomain: ComparableOnlyDomain<Path, T>,
    iterableDomain: IterableDomain<Path, T>
) : PathDomain<T>,
    PathOnlyDomain<T> by pathDomainOnlyImpl,
    ComparableOnlyDomain<Path, T> by comparableDomain,
    IterableDomain<Path, T> by iterableDomain {

    override val expect: Expect<T> = pathDomainOnlyImpl.expect
}

internal class PathOnlyDomainImpl<T : Path>(
    override val expect: Expect<T>

) : PathOnlyDomain<T> {
    override val fileName: ExtractedFeaturePostStep<T, String>
        get() = pathAssertions.fileName(expect)
    override val extension: ExtractedFeaturePostStep<T, String>
        get() = pathAssertions.extension(expect)
    override val fileNameWithoutExtension: ExtractedFeaturePostStep<T, String>
        get() = pathAssertions.fileNameWithoutExtension(expect)
    override val parent: ExtractedFeaturePostStep<T, Path>
        get() = pathAssertions.parent(expect)

    override fun exists(): Assertion =
        pathAssertions.exists(expect)

    override fun existsNot(): Assertion =
        pathAssertions.existsNot(expect)

    override fun isReadable(): Assertion =
        pathAssertions.isReadable(expect)

    override fun isWritable(): Assertion =
        pathAssertions.isWritable(expect)

    override fun isRegularFile(): Assertion =
        pathAssertions.isRegularFile(expect)

    override fun isDirectory(): Assertion =
        pathAssertions.isDirectory(expect)

    override fun startsWith(expected: Path): Assertion =
        pathAssertions.startsWith(expect, expected)

    override fun startsNotWith(expected: Path): Assertion =
        pathAssertions.startsNotWith(expect, expected)

    override fun endsWith(expected: Path): Assertion =
        pathAssertions.endsWith(expect, expected)

    override fun endsNotWith(expected: Path): Assertion =
        pathAssertions.endsNotWith(expect, expected)
}
