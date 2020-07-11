@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    DOES_NOT_HAVE_PARENT("!! does not have a parent"),
    ENDS_NOT_WITH("does not end with"),
    ENDS_WITH("ends with"),
    EXIST("exist"),
    EXTENSION("extension"),
    FILE_NAME("file name"),
    PARENT("parent"),
    FILE_NAME_WITHOUT_EXTENSION("file name without extension"),
    STARTS_WITH("starts with"),
    STARTS_NOT_WITH("does not start with"),
    READABLE("readable"),
    WRITABLE("writable"),
    A_FILE("a file"),
    A_DIRECTORY("a directory"),
    A_SYMBOLIC_LINK("a symbolic link"),
    A_UNKNOWN_FILE_TYPE("a unknown file type"),
    FAILURE_DUE_TO_NO_SUCH_FILE("no file system entry exists at this location"),
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT("%s exists at this location, but it is not %s"),
    HINT_OWNER("the owner is %s"),
    HINT_OWNER_AND_GROUP("the owner is %s, the group is %s"),
    HINT_ACTUAL_POSIX_PERMISSIONS("the permissions are %s"),
    HINT_ACTUAL_ACL_PERMISSIONS("the Access Control List is:"),
    FAILURE_DUE_TO_PARENT("failure at parent path"),
    FAILURE_DUE_TO_ACCESS_DENIED("access was denied"),
    FAILURE_DUE_TO_ACCESS_EXCEPTION("access threw a %s:"),
    FAILURE_DUE_TO_WRONG_FILE_TYPE("was %s instead of %s"),
    FAILURE_DUE_TO_LINK_LOOP("found a symbolic link loop: %s"),
    HINT_CLOSEST_EXISTING_PARENT_DIRECTORY("the closest existing parent directory is %s"),
    HINT_FOLLOWED_SYMBOLIC_LINK("followed the symbolic link %s to %s"),
    HAS_SAME_TEXTUAL_CONTENT("has same textual content with encoding %s, %s"),
    HAS_SAME_BINARY_CONTENT("has same binary content"),
}
