package ch.tutteli.atrium.logic.impl.creating.filesystem.hints

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.impl.creating.changers.ThrowableThrownFailureHandler
import ch.tutteli.atrium.logic.impl.creating.filesystem.Failure
import ch.tutteli.atrium.logic.impl.creating.filesystem.IoResult
import ch.tutteli.atrium.logic.impl.creating.filesystem.Success
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.followSymbolicLink
import ch.tutteli.niok.getFileAttributeView
import ch.tutteli.niok.readAttributes
import java.io.IOException
import java.nio.file.AccessDeniedException
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.attribute.*
import java.util.*

inline fun <T> Descriptive.DescriptionOption<Descriptive.FinalStep>.withIOExceptionFailureHint(
    expect: Expect<IoResult<T>>,
    crossinline f: (Path, IOException) -> Assertion?
): Descriptive.DescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep> =
    withFailureHintBasedOnDefinedSubject(expect) { result ->
        explainForResolvedLink(result.path) { realPath ->
            val exception = (result as Failure).exception
            f(realPath, exception) ?: hintForIoException(realPath, exception)
        }
    }

fun Descriptive.DescriptionOption<Descriptive.FinalStep>.withFileAttributesFailureHint(
    expect: Expect<IoResult<BasicFileAttributes>>
): Descriptive.DescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep> =
    withFailureHintBasedOnDefinedSubject(expect) { result ->
        explainForResolvedLink(result.path) { realPath ->
            when (result) {
                is Success -> describeWas(result.value.fileType)
                is Failure -> hintForIoException(realPath, result.exception)
            }
        }
    }

/**
 * Internal for testing purposes only
 */
fun explainForResolvedLink(
    path: Path,
    resolvedPathAssertionProvider: (realPath: Path) -> Assertion
): Assertion {
    val hintList = LinkedList<Assertion>()
    val realPath = addAllLevelResolvedSymlinkHints(path, hintList)
    val resolvedPathAssertion = resolvedPathAssertionProvider(realPath)
    return if (hintList.isNotEmpty()) {
        when (resolvedPathAssertion) {
            //TODO this should be done differently
            is AssertionGroup -> hintList.addAll(resolvedPathAssertion.assertions)
            else -> hintList.add(resolvedPathAssertion)
        }
        assertionBuilder.explanatoryGroup.withDefaultType
            .withAssertions(hintList)
            .build()
    } else {
        resolvedPathAssertion
    }
}


/**
 * Resolves the provided [path] and returns the resolved target (if resolving is possible).
 * Adds explanatory hints for all involved symbolic links to [hintList].
 */
private fun addAllLevelResolvedSymlinkHints(path: Path, hintList: Deque<Assertion>): Path {
    val absolutePath = path.toAbsolutePath().normalize()
    return addAllLevelResolvedSymlinkHints(absolutePath, hintList, Stack())
}

private fun addAllLevelResolvedSymlinkHints(
    absolutePath: Path,
    hintList: Deque<Assertion>,
    loopDetection: Stack<Path>
): Path {
    var currentPath = absolutePath.root

    for (part in absolutePath) {
        currentPath = currentPath.resolve(part)

        val loopDetectionIndex = loopDetection.indexOf(currentPath)
        if (loopDetectionIndex != -1) {
            // add to the list so [hintForLinkLoop] prints this duplicate twice
            loopDetection.add(currentPath)
            hintList.add(hintForLinkLoop(loopDetection, loopDetectionIndex))
            return absolutePath
        }

        val nextPathAfterFollowSymbolicLink = addOneStepResolvedSymlinkHint(currentPath, hintList)
        if (nextPathAfterFollowSymbolicLink != null) {
            loopDetection.push(currentPath)
            currentPath = addAllLevelResolvedSymlinkHints(nextPathAfterFollowSymbolicLink, hintList, loopDetection)
            loopDetection.pop()
        }
    }
    return currentPath
}

private fun hintForLinkLoop(loop: List<Path>, startIndex: Int): Assertion {
    val loopRepresentation = loop.subList(startIndex, loop.size).joinToString(" -> ")
    return assertionBuilder.explanatoryGroup.withWarningType
        .withExplanatoryAssertion(FAILURE_DUE_TO_LINK_LOOP, loopRepresentation)
        .build()
}

/**
 * If [absolutePath] is surely a symlink, adds an explanatory hint to [hintList] and returns the link target.
 * Return `null` and does not modify [hintList] otherwise.
 */
private fun addOneStepResolvedSymlinkHint(absolutePath: Path, hintList: Deque<Assertion>): Path? {
    // we use try-catch as a control flow structure,
    // where within the try we assume [absolutePath] to be a symbolic link
    return try {
        val nextPath = absolutePath
            .resolveSibling(absolutePath.followSymbolicLink())
            .normalize()

        hintList.add(
            assertionBuilder.explanatory
                .withExplanation(HINT_FOLLOWED_SYMBOLIC_LINK, absolutePath, nextPath)
                .build()
        )
        nextPath
    } catch (e: IOException) {
        // either this is not a link, or we cannot check it. The best we can do is assume it is not a link.
        null
    }
}

fun hintForIoException(path: Path, exception: IOException): Assertion = when (exception) {
    is NoSuchFileException -> hintForFileNotFound(path)
    else -> findHintForProblemWithParent(path) ?: hintForFileSpecificIoException(path, exception)
}

/**
 * Searches for any problem with a parent directory that is not that the directory does not exist.
 * @return an appropriate hint if a problem with a parent is found that is not that that parent does not exist.
 */
fun findHintForProblemWithParent(path: Path): Assertion? {
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

private val BasicFileAttributes.fileType: Translatable
    get() = when {
        isRegularFile -> A_FILE
        isDirectory -> A_DIRECTORY
        isSymbolicLink -> A_SYMBOLIC_LINK
        else -> A_UNKNOWN_FILE_TYPE
    }


private fun hintForParentFailure(parent: Path, explanation: Assertion) =
    assertionBuilder.explanatoryGroup.withDefaultType
        .withAssertions(
            assertionBuilder.descriptive.failing
                .withDescriptionAndRepresentation(FAILURE_DUE_TO_PARENT, parent)
                .build(),
            when (explanation) {
                is AssertionGroup -> explanation
                // TODO remove group once https://github.com/robstoll/atrium-roadmap/issues/1 is implemented
                else -> assertionBuilder.explanatoryGroup.withDefaultType
                    .withAssertion(explanation)
                    .build()
            }
        ).build()

fun hintForAccessDenied(path: Path): Assertion {
    val failureDueToAccessDeniedHint = assertionBuilder.explanatory
        .withExplanation(FAILURE_DUE_TO_ACCESS_DENIED)
        .build()
    return try {
        val hints = hintForOwnersAndPermissions(path)
        hints.add(0, failureDueToAccessDeniedHint)
        assertionBuilder.explanatoryGroup.withDefaultType
            .withAssertions(hints)
            .build()
    } catch (e: IOException) {
        failureDueToAccessDeniedHint
    }
}

fun hintForOwnersAndPermissions(path: Path): MutableList<Assertion> {
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
    assertionBuilder.explanatory
        .withExplanation(HINT_OWNER, owner)
        .build()

private fun hintForOwnerAndGroup(owner: String, group: String) =
    assertionBuilder.explanatory
        .withExplanation(HINT_OWNER_AND_GROUP, owner, group)
        .build()

private fun hintsForActualAclPermissions(acl: List<AclEntry>) =
    arrayOf(
        assertionBuilder.explanatory
            .withExplanation(HINT_ACTUAL_ACL_PERMISSIONS)
            .build(),
        assertionBuilder.explanatoryGroup.withDefaultType
            .withAssertions(acl.map(::hintForAclEntry))
            .build()
    )

private fun hintForAclEntry(entry: AclEntry) =
    assertionBuilder.explanatory
        .withExplanation("${entry.type()} ${entry.principal().name}: ${entry.permissions().joinToString()}")
        .build()

private fun hintForActualPosixPermissions(filePermissions: Set<PosixFilePermission>) =
    assertionBuilder.explanatory
        .withExplanation(HINT_ACTUAL_POSIX_PERMISSIONS, formatPosixPermissions(filePermissions))
        .build()

private fun formatPosixPermissions(filePermissions: Set<PosixFilePermission>): StringBuilder {
    val permissionString = StringBuilder(3 * 5 + 2)
    permissionString
        .append("u=")
        .append(
            toPermissionString(
                filePermissions,
                PosixFilePermission.OWNER_READ,
                PosixFilePermission.OWNER_WRITE,
                PosixFilePermission.OWNER_EXECUTE
            )
        )
        .append(' ')
        .append("g=")
        .append(
            toPermissionString(
                filePermissions,
                PosixFilePermission.GROUP_READ,
                PosixFilePermission.GROUP_WRITE,
                PosixFilePermission.GROUP_EXECUTE
            )
        )
        .append(' ')
        .append("o=")
        .append(
            toPermissionString(
                filePermissions,
                PosixFilePermission.OTHERS_READ,
                PosixFilePermission.OTHERS_WRITE,
                PosixFilePermission.OTHERS_EXECUTE
            )
        )
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

fun <T : Path> hintForExistsButMissingPermission(subject: T, permissionName: Translatable): Assertion =
    assertionBuilder.explanatory
        .withExplanation(
            FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT,
            subject.readAttributes<BasicFileAttributes>().fileType,
            permissionName
        )
        .build()

private fun describeWas(actual: Translatable) =
    assertionBuilder.descriptive
        .failing
        .withDescriptionAndRepresentation(DescriptionBasic.WAS, actual)
        .build()


private fun hintForFileSpecificIoException(path: Path, exception: IOException) =
    when (exception) {
        is AccessDeniedException -> hintForAccessDenied(path)
        else -> hintForOtherIoException(exception)
    }

private fun hintForFileNotFound(path: Path) =
    assertionBuilder.explanatoryGroup
        .withDefaultType
        .withAssertions(
            hintForNoSuchFile(),
            hintForClosestExistingParent(path)
        )
        .build()

private fun hintForNoSuchFile() =
    assertionBuilder.explanatory
        .withExplanation(FAILURE_DUE_TO_NO_SUCH_FILE)
        .build()

/**
 * Assumes that we know that [path] does not exist.
 * @return The closest parent directory (including [path] itself) that exists. `null` if there is no such directory.
 */
fun hintForClosestExistingParent(path: Path): Assertion {
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
    assertionBuilder.explanatory
        .withExplanation(HINT_CLOSEST_EXISTING_PARENT_DIRECTORY, parent ?: DescriptionBasic.NONE)
        .build()

private fun hintForNotDirectory(actualType: Translatable) =
    assertionBuilder.explanatory
        .withExplanation(
            FAILURE_DUE_TO_WRONG_FILE_TYPE, actualType,
            A_DIRECTORY
        )
        .build()

private fun hintForOtherIoException(exception: IOException) =
    ThrowableThrownFailureHandler.propertiesOfThrowable(
        exception,
        explanation = assertionBuilder.explanatory
            .withExplanation(
                FAILURE_DUE_TO_ACCESS_EXCEPTION,
                exception::class.simpleName ?: exception::class.fullName
            )
            .build()
    )
