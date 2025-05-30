//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.assertions.builders.withHelpOnFailureBasedOnDefinedSubject
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.filesystem.*
import ch.tutteli.atrium.logic.creating.filesystem.hints.*
import ch.tutteli.atrium.logic.creating.filesystem.runCatchingIo
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.*
import java.nio.charset.Charset
import java.nio.file.*
import java.nio.file.LinkOption.NOFOLLOW_LINKS
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

    override fun <T : Path> exists(container: AssertionContainer<T>, linkOption: LinkOption?): Assertion =
        changeSubjectToFileAttributes(container, linkOption) { fileAttributesExpect ->
            assertionBuilder.descriptive
                .withTest(fileAttributesExpect) { it is Success }
                .withHelpOnIOExceptionFailure(fileAttributesExpect) { realPath, exception ->
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


    override fun <T : Path> existsNot(container: AssertionContainer<T>, linkOption: LinkOption?): Assertion =
        changeSubjectToFileAttributes(container, linkOption) { fileAttributesExpect ->
            assertionBuilder.descriptive
                .withTest(fileAttributesExpect) { it is Failure && it.exception is NoSuchFileException }
                .withHelpOnFileAttributesFailure(fileAttributesExpect)
                .withDescriptionAndRepresentation(DescriptionBasic.NOT_TO, EXIST)
                .build()
        }

    private inline fun <T : Path, R> changeSubjectToFileAttributes(
        container: AssertionContainer<T>,
        linkOption: LinkOption? = null,
        block: (Expect<IoResult<BasicFileAttributes>>) -> R
    ): R = container.changeSubject.unreported {
        it.runCatchingIo<BasicFileAttributes> {
            if (linkOption == null) readAttributes() else readAttributes(linkOption)
        }
    }.let(block)

    override fun <T : Path> isReadable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, READABLE, AccessMode.READ, shouldHaveAccess = true)

    override fun <T : Path> isNotReadable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, READABLE, AccessMode.READ, shouldHaveAccess = false)

    override fun <T : Path> isWritable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, WRITABLE, AccessMode.WRITE, shouldHaveAccess = true)

    override fun <T : Path> isNotWritable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, WRITABLE, AccessMode.WRITE, shouldHaveAccess = false)

    override fun <T : Path> isExecutable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, EXECUTABLE, AccessMode.EXECUTE, shouldHaveAccess = true)

    override fun <T : Path> isNotExecutable(container: AssertionContainer<T>): Assertion =
        filePermissionAssertion(container, EXECUTABLE, AccessMode.EXECUTE, shouldHaveAccess = false)

    override fun <T : Path> isRegularFile(container: AssertionContainer<T>): Assertion =
        fileTypeAssertion(container, A_FILE) { it.isRegularFile }

    override fun <T : Path> isDirectory(container: AssertionContainer<T>): Assertion =
        fileTypeAssertion(container, A_DIRECTORY) { it.isDirectory }

    override fun <T : Path> isSymbolicLink(container: AssertionContainer<T>): Assertion =
        fileTypeAssertion(container, A_SYMBOLIC_LINK, NOFOLLOW_LINKS) { it.isSymbolicLink }

    override fun <T : Path> isAbsolute(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_BE, ABSOLUTE_PATH) { it.isAbsolute }

    override fun <T : Path> isRelative(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_BE, RELATIVE_PATH) { !it.isAbsolute }

    private fun <T : Path> filePermissionAssertion(
        container: AssertionContainer<T>,
        permissionName: Translatable,
        accessMode: AccessMode,
        shouldHaveAccess: Boolean
    ) = container.changeSubject.unreported {
        it.runCatchingIo { fileSystem.provider().checkAccess(this, accessMode) }
    }.let { checkAccessResultExpect ->
        assertionBuilder.descriptive
            .withTest(checkAccessResultExpect) {
                if (shouldHaveAccess) it is Success
                else it is Failure && it.exception is AccessDeniedException
            }
            .withHelpOnIOExceptionFailure(checkAccessResultExpect) { realPath, exception ->
                when (exception) {
                    is AccessDeniedException -> checkAccessResultExpect._logic.findHintForProblemWithParent(realPath)
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
            .withDescriptionAndRepresentation(if (shouldHaveAccess) TO_BE else NOT_TO_BE, permissionName)
            .build()
    }

    private inline fun <T : Path> fileTypeAssertion(
        container: AssertionContainer<T>,
        typeName: Translatable,
        linkOption: LinkOption? = null,
        crossinline typeTest: (BasicFileAttributes) -> Boolean
    ) = changeSubjectToFileAttributes(container, linkOption) { fileAttributesExpect ->
        assertionBuilder.descriptive
            .withTest(fileAttributesExpect) { it is Success && typeTest(it.value) }
            .withHelpOnFileAttributesFailure(fileAttributesExpect)
            .withDescriptionAndRepresentation(TO_BE, typeName)
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

    override fun <T : Path> hasDirectoryEntry(container: AssertionContainer<T>, entries: List<String>): Assertion =
        assertionBuilder.invisibleGroup.withAssertions(
            listOf(container.isDirectory()) +
                entries.map { entry ->
                    container.resolve(entry).collect { _logicAppend { exists(NOFOLLOW_LINKS) } }
                }
        ).build()

    override fun <T : Path> isEmptyDirectory(container: AssertionContainer<T>): Assertion {
        val isDirectory = container.isDirectory()
        if (isDirectory.holds()) {
            val showMax = 10
            return container.changeSubject.unreported {
                it.runCatchingIo { Files.newDirectoryStream(it).use { stream -> stream.take(showMax + 1) } }
            }.let { expectResult ->
                assertionBuilder.descriptive
                    .withTest(expectResult) { it is Success && it.value.isEmpty() }
                    .withHelpOnFailureBasedOnDefinedSubject(expectResult) { ioResult ->
                        explainForResolvedLink(ioResult.path) { realPath ->
                            when (ioResult) {
                                is Success -> {
                                    val maxEntries = ioResult.value
                                        .asSequence()
                                        .sortedBy { it.fileNameAsString }
                                        .take(showMax)
                                        .map { path ->
                                            assertionBuilder.representationOnly
                                                .holding
                                                .withRepresentation(ioResult.path.relativize(path))
                                                .build()
                                        }
                                    val entries = if (ioResult.value.size > showMax) {
                                        maxEntries +
                                            assertionBuilder.representationOnly.holding.withRepresentation(Text("..."))
                                                .build()
                                    } else {
                                        maxEntries
                                    }
                                    assertionBuilder.explanatoryGroup
                                        .withWarningType
                                        .withAssertion(
                                            assertionBuilder.list
                                                .withDescriptionAndEmptyRepresentation(DIRECTORY_CONTAINS)
                                                .withAssertions(entries.toList())
                                                .build()
                                        )
                                        .build()
                                }
                                is Failure -> expectResult._logic.hintForIoException(realPath, ioResult.exception)
                            }
                        }
                    }
                    .withDescriptionAndRepresentation(TO_BE, AN_EMPTY_DIRECTORY)
                    .build()
            }
        } else return isDirectory
    }
}
