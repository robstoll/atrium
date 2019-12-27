@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.withFailureHintBasedOnDefinedSubject
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem.*
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.ENDS_NOT_WITH
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.extension
import ch.tutteli.niok.fileNameWithoutExtension
import ch.tutteli.niok.getFileAttributeView
import ch.tutteli.niok.readAttributes
import java.io.IOException
import java.nio.file.AccessDeniedException
import java.nio.file.AccessMode
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.attribute.*
import java.nio.file.attribute.PosixFilePermission.*
import java.util.*

private const val IO_EXCEPTION_STACK_TRACE_LENGTH = 15

fun <T : Path> _startsWith(assertionContainer: Expect<T>, expected: Path): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, STARTS_WITH, expected) { it.startsWith(expected) }

fun <T : Path> _startsNotWith(assertionContainer: Expect<T>, expected: Path): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, STARTS_NOT_WITH, expected) { !it.startsWith(expected) }

fun <T : Path> _endsWith(assertionContainer: Expect<T>, expected: Path): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, ENDS_WITH, expected) { it.endsWith(expected) }

fun <T : Path> _endsNotWith(assertionContainer: Expect<T>, expected: Path) =
    ExpectImpl.builder.createDescriptive(assertionContainer, ENDS_NOT_WITH, expected) { !it.endsWith(expected) }

fun <T : Path> _exists(assertionContainer: Expect<T>): Assertion =
    changeSubjectToFileAttributes(assertionContainer) { fileAttributesAssertionContainer ->
        ExpectImpl.builder.descriptive
            .withTest(fileAttributesAssertionContainer) { it is Success }
            .withFailureHintBasedOnDefinedSubject(fileAttributesAssertionContainer) { result ->
                explainForResolvedLink(result.path) { realPath ->
                    val exception = (result as Failure).exception
                    when (exception) {
                        // TODO remove group once https://github.com/robstoll/atrium-roadmap/issues/1 is implemented
                        is NoSuchFileException -> ExpectImpl.builder.explanatoryGroup.withDefaultType.withAssertion(
                            hintForClosestExistingParent(realPath)
                        ).build()
                        else -> hintForIoException(realPath, exception)
                    }
                }
            }
            .withDescriptionAndRepresentation(TO, RawString.create(EXIST))
            .build()
    }

fun <T : Path> _existsNot(assertionContainer: Expect<T>): Assertion =
    changeSubjectToFileAttributes(assertionContainer) { fileAttributesAssertionContainer ->
        ExpectImpl.builder.descriptive
            .withTest(fileAttributesAssertionContainer) { it is Failure && it.exception is NoSuchFileException }
            .withFailureHintBasedOnDefinedSubject(fileAttributesAssertionContainer) { result ->
                explainForResolvedLink(result.path) { realPath ->
                    when (result) {
                        is Success -> describeWas(result.value.fileType)
                        is Failure -> hintForIoException(realPath, result.exception)
                    }
                }
            }
            .withDescriptionAndRepresentation(NOT_TO, RawString.create(EXIST))
            .build()
    }

fun <T : Path> _isReadable(assertionContainer: Expect<T>): Assertion =
    filePermissionAssertion(assertionContainer, READABLE, AccessMode.READ)

fun <T : Path> _isWritable(assertionContainer: Expect<T>): Assertion =
    filePermissionAssertion(assertionContainer, WRITABLE, AccessMode.WRITE)

fun <T : Path> _isRegularFile(assertionContainer: Expect<T>): Assertion =
    fileTypeAssertion(assertionContainer, A_FILE) { it.isRegularFile }

fun <T : Path> _isDirectory(assertionContainer: Expect<T>): Assertion =
    fileTypeAssertion(assertionContainer, A_DIRECTORY) { it.isDirectory }

private fun <T : Path> filePermissionAssertion(
    assertionContainer: Expect<T>,
    permissionName: Translatable,
    accessMode: AccessMode
) = ExpectImpl.changeSubject(assertionContainer).unreported {
    it.runCatchingIo { fileSystem.provider().checkAccess(it, accessMode) }
}.let { checkAccessResultContainer ->
    ExpectImpl.builder.descriptive
        .withTest(checkAccessResultContainer) { it is Success }
        .withFailureHintBasedOnDefinedSubject(checkAccessResultContainer) { result ->
            explainForResolvedLink(result.path) { realPath ->
                val exception = (result as Failure).exception
                when (exception) {
                    is AccessDeniedException -> findHintForProblemWithParent(realPath)
                        ?: ExpectImpl.builder.explanatoryGroup.withDefaultType
                            .withAssertions(
                                listOf(hintForExistsButMissingPermission(realPath, permissionName))
                                    + hintForOwnersAndPermissions(realPath)
                            )
                            .build()
                    else -> hintForIoException(realPath, exception)
                }
            }
        }
        .withDescriptionAndRepresentation(TO_BE, RawString.create(permissionName))
        .build()
}

private inline fun <T : Path> fileTypeAssertion(
    assertionContainer: Expect<T>,
    typeName: Translatable,
    crossinline typeTest: (BasicFileAttributes) -> Boolean
) = changeSubjectToFileAttributes(assertionContainer) { fileAttributesAssertionContainer ->
    ExpectImpl.builder.descriptive
        .withTest(fileAttributesAssertionContainer) { it is Success && typeTest(it.value) }
        .withFailureHintBasedOnDefinedSubject(fileAttributesAssertionContainer) { result ->
            explainForResolvedLink(result.path) { realPath ->
                when (result) {
                    is Success -> describeWas(result.value.fileType)
                    is Failure -> hintForIoException(realPath, result.exception)
                }
            }
        }
        .withDescriptionAndRepresentation(TO_BE, RawString.create(typeName))
        .build()
}

private inline fun <T : Path, R> changeSubjectToFileAttributes(
    assertionContainer: Expect<T>,
    block: (Expect<IoResult<BasicFileAttributes>>) -> R
): R = ExpectImpl.changeSubject(assertionContainer).unreported {
    it.runCatchingIo { readAttributes<BasicFileAttributes>() }
}.let(block)

/**
 * Searches for any problem with a parent directory that is not that the directory does not exist.
 * @return an appropriate hint if a problem with a parent is found that is not that that parent does not exist.
 */
private fun findHintForProblemWithParent(path: Path): Assertion? {
    val absolutePath = path.toAbsolutePath()
    var currentParentPart = absolutePath.root
    for (part in absolutePath) {
        currentParentPart = currentParentPart.resolve(part)
        if (currentParentPart != path) {
            try {
                val attributes = currentParentPart.readAttributes<BasicFileAttributes>()
                if (!attributes.isDirectory) {
                    return hintForParentFailure(
                        currentParentPart,
                        explanation = hintForNotDirectory(attributes.fileType)
                    )
                }
            } catch (e: AccessDeniedException) {
                return hintForParentFailure(
                    currentParentPart.parent,
                    explanation = hintForAccessDenied(currentParentPart.parent)
                )
            } catch (e: IOException) {
                return hintForParentFailure(
                    currentParentPart,
                    explanation = hintForFileSpecificIoException(currentParentPart, e)
                )
            }
        }
    }
    return null
}

private fun hintForParentFailure(parent: Path, explanation: Assertion) =
    ExpectImpl.builder.explanatoryGroup.withDefaultType
        .withAssertions(
            ExpectImpl.builder.descriptive.failing
                .withDescriptionAndRepresentation(FAILURE_DUE_TO_PARENT, parent)
                .build(),
            when (explanation) {
                is AssertionGroup -> explanation
                // TODO remove group once https://github.com/robstoll/atrium-roadmap/issues/1 is implemented
                else -> ExpectImpl.builder.explanatoryGroup.withDefaultType
                    .withAssertion(explanation)
                    .build()
            }
        ).build()

private fun hintForAccessDenied(path: Path): Assertion {
    val failureDueToAccessDeniedHint = ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_ACCESS_DENIED)
        .build()
    return try {
        val hints = hintForOwnersAndPermissions(path)
        hints.add(0, failureDueToAccessDeniedHint)
        ExpectImpl.builder.explanatoryGroup.withDefaultType
            .withAssertions(hints)
            .build()
    } catch (e: IOException) {
        failureDueToAccessDeniedHint
    }
}

private fun hintForOwnersAndPermissions(path: Path): MutableList<Assertion> {
    val hintList = LinkedList<Assertion>()
    val aclView = path.getFileAttributeView<AclFileAttributeView>()
    if (aclView != null) {
        hintList.add(hintForOwner(aclView.owner.name))
        hintList.addAll(hintsForActualAclPermissions(aclView.acl))
    } else {
        val posixView = path.getFileAttributeView<PosixFileAttributeView>()
        if (posixView != null) {
            val posixAttributes = posixView.readAttributes()
            hintList.add(hintForOwnerAndGroup(posixAttributes.owner().name, posixAttributes.group().name))
            hintList.add(hintForActualPosixPermissions(posixAttributes.permissions()))
        }
    }
    return hintList
}

private fun hintForOwner(owner: String) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_OWNER, owner)
        .build()

private fun hintForOwnerAndGroup(owner: String, group: String) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_OWNER_AND_GROUP, owner, group)
        .build()

private fun hintsForActualAclPermissions(acl: List<AclEntry>) =
    arrayOf(
        ExpectImpl.builder.explanatory
            .withExplanation(HINT_ACTUAL_ACL_PERMISSIONS)
            .build(),
        ExpectImpl.builder.explanatoryGroup.withDefaultType
            .withAssertions(acl.map(::hintForAclEntry))
            .build()
    )

private fun hintForAclEntry(entry: AclEntry) =
    ExpectImpl.builder.explanatory
        .withExplanation("${entry.type()} ${entry.principal().name}: ${entry.permissions().joinToString()}")
        .build()

private fun hintForActualPosixPermissions(filePermissions: Set<PosixFilePermission>) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_ACTUAL_POSIX_PERMISSIONS, formatPosixPermissions(filePermissions))
        .build()

private fun formatPosixPermissions(filePermissions: Set<PosixFilePermission>): StringBuilder {
    val permissionString = StringBuilder(3 * 5 + 2)
    permissionString
        .append("u=")
        .append(toPermissionString(filePermissions, OWNER_READ, OWNER_WRITE, OWNER_EXECUTE))
        .append(' ')
        .append("g=")
        .append(toPermissionString(filePermissions, GROUP_READ, GROUP_WRITE, GROUP_EXECUTE))
        .append(' ')
        .append("o=")
        .append(toPermissionString(filePermissions, OTHERS_READ, OTHERS_WRITE, OTHERS_EXECUTE))
    return permissionString
}

private fun toPermissionString(
    permissions: Set<PosixFilePermission>,
    readPermission: PosixFilePermission,
    writePermission: PosixFilePermission,
    executePermission: PosixFilePermission
): StringBuilder {
    val result = StringBuilder(3)
    if (permissions.contains(readPermission)) result.append('r')
    if (permissions.contains(writePermission)) result.append('w')
    if (permissions.contains(executePermission)) result.append('x')
    return result
}

private fun <T : Path> hintForExistsButMissingPermission(subject: T, permissionName: Translatable) =
    ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT, subject.fileType, permissionName)
        .build()

private fun describeWas(actual: Translatable) =
    ExpectImpl.builder.descriptive.failing
        .withDescriptionAndRepresentation(WAS, RawString.create(actual))
        .build()

private fun hintForIoException(path: Path, exception: IOException) = when (exception) {
    is NoSuchFileException -> hintForFileNotFound(path)
    else -> findHintForProblemWithParent(path) ?: hintForFileSpecificIoException(path, exception)
}

private fun hintForFileSpecificIoException(path: Path, exception: IOException) =
    when (exception) {
        is AccessDeniedException -> hintForAccessDenied(path)
        else -> hintForOtherIoException(exception)
    }

private fun hintForFileNotFound(path: Path) =
    ExpectImpl.builder.explanatoryGroup.withDefaultType
        .withAssertions(
            hintForNoSuchFile(),
            hintForClosestExistingParent(path)
        )
        .build()

private fun hintForNoSuchFile() =
    ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_NO_SUCH_FILE)
        .build()

/**
 * Assumes that we know that [path] does not exist.
 * @return The closest parent directory (including [path] itself) that exists. `null` if there is no such directory.
 */
private fun hintForClosestExistingParent(path: Path): Assertion {
    var testPath = path.toAbsolutePath().parent
    while (testPath.nameCount > 0) {
        try {
            val testPathAttributes = testPath.readAttributes<BasicFileAttributes>()
            return if (testPathAttributes.isDirectory) {
                hintForExistingParentDirectory(testPath)
            } else {
                hintForParentFailure(
                    testPath,
                    explanation = hintForNotDirectory(testPathAttributes.fileType)
                )
            }
        } catch (e: NoSuchFileException) {
            /* continue searching. Any other IOException should not occur because [path] does not exist */
        }
        testPath = testPath.parent
    }
    return hintForExistingParentDirectory(null)
}

private fun hintForExistingParentDirectory(parent: Path?) =
    ExpectImpl.builder.explanatory
        .withExplanation(HINT_CLOSEST_EXISTING_PARENT_DIRECTORY, parent ?: NONE)
        .build()

private fun hintForNotDirectory(actualType: Translatable) =
    ExpectImpl.builder.explanatory
        .withExplanation(FAILURE_DUE_TO_WRONG_FILE_TYPE, actualType, A_DIRECTORY)
        .build()

private fun hintForOtherIoException(exception: IOException) =
    ThrowableThrownFailureHandler.propertiesOfThrowable(
        exception,
        maxStackTrace = IO_EXCEPTION_STACK_TRACE_LENGTH,
        explanation = AssertImpl.builder.explanatory
            .withExplanation(
                FAILURE_DUE_TO_ACCESS_EXCEPTION,
                exception::class.simpleName ?: exception::class.fullName
            )
            .build()
    )

private val Path.fileType: Translatable
    get() = readAttributes<BasicFileAttributes>().fileType

private val BasicFileAttributes.fileType: Translatable
    get() = when {
        isRegularFile -> A_FILE
        isDirectory -> A_DIRECTORY
        isSymbolicLink -> A_SYMBOLIC_LINK
        else -> A_UNKNOWN_FILE_TYPE
    }

fun <T : Path> _fileName(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, String> =
    ExpectImpl.feature.manualFeature(assertionContainer, FILE_NAME) { fileName.toString() }

fun <T : Path> _fileNameWithoutExtension(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, String> =
    ExpectImpl.feature.manualFeature(assertionContainer, FILE_NAME_WITHOUT_EXTENSION) { fileNameWithoutExtension }

fun <T : Path> _parent(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Path> =
    ExpectImpl.feature.extractor(assertionContainer)
        .withDescription(PARENT)
        .withRepresentationForFailure(DOES_NOT_HAVE_PARENT)
        .withFeatureExtraction {
            val parent: Path? = it.parent
            if(parent != null) Some(parent) else None
        }
        .withoutOptions()
        .build()

fun <T : Path> _extension(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, String> =
    ExpectImpl.feature.manualFeature(assertionContainer, EXTENSION) { extension }
