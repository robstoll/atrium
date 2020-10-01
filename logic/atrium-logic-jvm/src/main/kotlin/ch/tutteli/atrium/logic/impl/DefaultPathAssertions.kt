@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.filesystem.Failure
import ch.tutteli.atrium.logic.creating.filesystem.IoResult
import ch.tutteli.atrium.logic.creating.filesystem.Success
import ch.tutteli.atrium.logic.creating.filesystem.hints.*
import ch.tutteli.atrium.logic.creating.filesystem.runCatchingIo
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.*
import java.nio.charset.Charset
import java.nio.file.AccessDeniedException
import java.nio.file.AccessMode
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

class DefaultPathAssertions : PathAssertions {
    override fun <T : Path> startsWith(container: AssertionContainer<T>, expected: Path): Assertion =
        container.createDescriptiveAssertion(STARTS_WITH, expected) { it.startsWith(expected) }

    override fun <T : Path> startsNotWith(container: AssertionContainer<T>, expected: Path): Assertion =
        container.createDescriptiveAssertion(STARTS_NOT_WITH, expected) { !it.startsWith(expected) }

    override fun <T : Path> endsWith(container: AssertionContainer<T>, expected: Path): Assertion =
        container.createDescriptiveAssertion(ENDS_WITH, expected) { it.endsWith(expected) }

    override fun <T : Path> endsNotWith(container: AssertionContainer<T>, expected: Path): Assertion =
        container.createDescriptiveAssertion(ENDS_NOT_WITH, expected) { !it.endsWith(expected) }

    override fun <T : Path> hasSameTextualContentAs(
        container: AssertionContainer<T>,
        targetPath: Path,
        sourceCharset: Charset,
        targetCharset: Charset
    ): Assertion = container.createDescriptiveAssertion(
        TranslatableWithArgs(HAS_SAME_TEXTUAL_CONTENT, sourceCharset, targetCharset),
        targetPath
    ) {
        it.readText(sourceCharset) == targetPath.readText(targetCharset)
    }

    override fun <T : Path> hasSameBinaryContentAs(container: AssertionContainer<T>, targetPath: Path): Assertion =
        container.createDescriptiveAssertion(HAS_SAME_BINARY_CONTENT, targetPath) {
            it.readAllBytes().contentEquals(targetPath.readAllBytes())
        }

    override fun <T : Path> exists(container: AssertionContainer<T>): Assertion =
        changeSubjectToFileAttributes(container) { fileAttributesExpect ->
            assertionBuilder.descriptive
                .withTest(fileAttributesExpect) { it is Success }
                .withIOExceptionFailureHint(fileAttributesExpect) { realPath, exception ->
                    when (exception) {
                        // TODO remove group once https://github.com/robstoll/atrium-roadmap/issues/1 is implemented
                        is NoSuchFileException -> assertionBuilder.explanatoryGroup
                            .withDefaultType
                            .withAssertion(hintForClosestExistingParent(realPath))
                            .build()
                        else -> null
                    }
                }
                .withDescriptionAndRepresentation(DescriptionBasic.TO, EXIST)
                .build()
        }

    override fun <T : Path> existsNot(container: AssertionContainer<T>): Assertion =
        changeSubjectToFileAttributes(container) { fileAttributesExpect ->
            assertionBuilder.descriptive
                .withTest(fileAttributesExpect) { it is Failure && it.exception is NoSuchFileException }
                .withFileAttributesFailureHint(fileAttributesExpect)
                .withDescriptionAndRepresentation(DescriptionBasic.NOT_TO, EXIST)
                .build()
        }

    private inline fun <T : Path, R> changeSubjectToFileAttributes(
        container: AssertionContainer<T>,
        block: (Expect<IoResult<BasicFileAttributes>>) -> R
    ): R = container.changeSubject.unreported {
        it.runCatchingIo { readAttributes<BasicFileAttributes>() }
    }.let(block)

    override fun <T : Path> isReadable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, READABLE, AccessMode.READ)

    override fun <T : Path> isWritable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, WRITABLE, AccessMode.WRITE)

    override fun <T : Path> isExecutable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, EXECUTABLE, AccessMode.EXECUTE)

    override fun <T : Path> isRegularFile(container: AssertionContainer<T>): Assertion =
        fileTypeAssertion(container, A_FILE) { it.isRegularFile }

    override fun <T : Path> isDirectory(container: AssertionContainer<T>): Assertion =
        fileTypeAssertion(container, A_DIRECTORY) { it.isDirectory }

    private fun <T : Path> filePermissionAssertion(
        container: AssertionContainer<T>,
        permissionName: Translatable,
        accessMode: AccessMode
    ) = container.changeSubject.unreported {
        it.runCatchingIo { fileSystem.provider().checkAccess(it, accessMode) }
    }.let { checkAccessResultExpect ->
        assertionBuilder.descriptive
            .withTest(checkAccessResultExpect) { it is Success }
            .withIOExceptionFailureHint(checkAccessResultExpect) { realPath, exception ->
                when (exception) {
                    is AccessDeniedException -> findHintForProblemWithParent(realPath)
                        ?: assertionBuilder.explanatoryGroup
                            .withDefaultType
                            .withAssertions(
                                listOf(hintForExistsButMissingPermission(realPath, permissionName))
                                    + hintForOwnersAndPermissions(realPath)
                            )
                            .build()
                    else -> null
                }
            }
            .withDescriptionAndRepresentation(DescriptionBasic.IS, permissionName)
            .build()
    }

    private inline fun <T : Path> fileTypeAssertion(
        container: AssertionContainer<T>,
        typeName: Translatable,
        crossinline typeTest: (BasicFileAttributes) -> Boolean
    ) = changeSubjectToFileAttributes(container) { fileAttributesExpect ->
        assertionBuilder.descriptive
            .withTest(fileAttributesExpect) { it is Success && typeTest(it.value) }
            .withFileAttributesFailureHint(fileAttributesExpect)
            .withDescriptionAndRepresentation(DescriptionBasic.IS, typeName)
            .build()
    }


    override fun <T : Path> fileName(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, String> =
        container.manualFeature(FILE_NAME) { fileName.toString() }

    override fun <T : Path> extension(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, String> =
        container.manualFeature(EXTENSION) { extension }

    override fun <T : Path> fileNameWithoutExtension(
        container: AssertionContainer<T>
    ): FeatureExtractorBuilder.ExecutionStep<T, String> =
        container.manualFeature(FILE_NAME_WITHOUT_EXTENSION) { fileNameWithoutExtension }

    override fun <T : Path> parent(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, Path> =
        container.extractFeature
            .withDescription(PARENT)
            .withRepresentationForFailure(DOES_NOT_HAVE_PARENT)
            .withFeatureExtraction {
                val parent: Path? = it.parent
                if (parent != null) Some(parent) else None
            }
            .withoutOptions()
            .build()

    override fun <T : Path> resolve(
        container: AssertionContainer<T>,
        other: String
    ): FeatureExtractorBuilder.ExecutionStep<T, Path> = container.f1<T, String, Path>(Path::resolve, other)

}
