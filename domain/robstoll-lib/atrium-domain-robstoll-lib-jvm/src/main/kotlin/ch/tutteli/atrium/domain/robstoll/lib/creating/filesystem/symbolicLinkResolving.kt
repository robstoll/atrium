package ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionPathAssertion.FAILURE_DUE_TO_LINK_LOOP
import ch.tutteli.atrium.translations.DescriptionPathAssertion.HINT_FOLLOWED_SYMBOLIC_LINK
import ch.tutteli.niok.followSymbolicLink
import java.io.IOException
import java.nio.file.Path
import java.util.*

inline fun explainForResolvedLink(path: Path, assertionCreator: (realPath: Path) -> Assertion): Assertion {
    val hintList = LinkedList<Assertion>()
    val realPath = addAllLevelResolvedSymlinkHints(path, hintList)
    val createdAssertion = assertionCreator(realPath)
    return if (hintList.isNotEmpty()) {
        when (createdAssertion) {
            is AssertionGroup -> hintList.addAll(createdAssertion.assertions)
            else -> hintList.add(createdAssertion)
        }
        ExpectImpl.builder.explanatoryGroup.withDefaultType
            .withAssertions(hintList)
            .build()
    } else {
        createdAssertion
    }
}

/**
 * Resolves the provided [path] and returns the resolved target (if resolving is possible).
 * Adds explanatory hints for all involved symbolic links to [hintList].
 */
@PublishedApi
internal fun addAllLevelResolvedSymlinkHints(path: Path, hintList: Deque<Assertion>): Path {
    val absolutePath = path.toAbsolutePath().normalize()
    return addAllLevelResolvedSymlinkHints(absolutePath, hintList, LinkedList(), absolutePath)
}

private fun addAllLevelResolvedSymlinkHints(
    absolutePath: Path,
    hintList: Deque<Assertion>,
    visitedList: MutableList<Path>,
    initialPath: Path
): Path {
    var currentPath = absolutePath.root
    visitedList.add(absolutePath)

    for (part in absolutePath) {
        currentPath = currentPath.resolve(part)
        val nextPath = addOneStepResolvedSymlinkHint(
            currentPath,
            hintList
        )
        if (nextPath != null) {
            val visitedIndex = visitedList.indexOf(nextPath)
            if (visitedIndex != -1) {
                repeat(hintList.size - visitedIndex) { hintList.removeLast() }
                // add to the list so [hintForLinkLoop] prints this duplicate twice
                visitedList.add(nextPath)
                hintList.add(hintForLinkLoop(visitedList, visitedIndex))
                return initialPath
            } else {
                currentPath = addAllLevelResolvedSymlinkHints(nextPath, hintList, visitedList, initialPath)
            }
        }
    }
    return currentPath
}

/**
 * If [absolutePath] is surely a symlink, adds an explanatory hint to [hintList] and returns the link target.
 * Return `null` and does not modify [hintList] otherwise.
 */
private fun addOneStepResolvedSymlinkHint(absolutePath: Path, hintList: Deque<Assertion>) = try {
    val nextPath = absolutePath
        .resolveSibling(absolutePath.followSymbolicLink())
        .normalize()
    hintList.add(
        ExpectImpl.builder.explanatory
            .withExplanation(HINT_FOLLOWED_SYMBOLIC_LINK, absolutePath, nextPath)
            .build()
    )
    nextPath
} catch (e: IOException) {
    // either this is not a link, or we cannot check it. The best we can do is assume it is not a link.
    null
}

private fun hintForLinkLoop(loop: List<Path>, startIndex: Int): Assertion {
    val loopRepresentation = loop.subList(startIndex, loop.size).joinToString(" -> ")
    return ExpectImpl.builder.explanatoryGroup.withWarningType
        .withExplanatoryAssertion(FAILURE_DUE_TO_LINK_LOOP, loopRepresentation)
        .build()
}
