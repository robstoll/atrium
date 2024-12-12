package ch.tutteli.atrium.reporting.reportables.descriptions

import ch.tutteli.atrium.reporting.reportables.Description

//TODO 1.3.0 replace translation with args, i.e. those with %s
enum class DescriptionPathProof(override val string: String) : Description {
    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    ABSOLUTE_PATH("an absolute path"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    DOES_NOT_HAVE_PARENT("!! does not have a parent"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    ENDS_NOT_WITH("does not end with"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    ENDS_WITH("ends with"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    EXIST("exist"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    EXTENSION("extension"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FILE_NAME("file name"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    PARENT("parent"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FILE_NAME_WITHOUT_EXTENSION("file name without extension"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    STARTS_WITH("starts with"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    STARTS_NOT_WITH("does not start with"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    READABLE("readable"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    WRITABLE("writable"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    EXECUTABLE("executable"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    A_FILE("a file"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    A_DIRECTORY("a directory"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    AN_EMPTY_DIRECTORY("an empty directory"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    DIRECTORY_CONTAINS("directory contains"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    A_SYMBOLIC_LINK("a symbolic link"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    A_UNKNOWN_FILE_TYPE("a unknown file type"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FAILURE_DUE_TO_NO_SUCH_FILE("no file system entry exists at this location"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT("%s exists at this location, but it is not %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HINT_OWNER("the owner is %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HINT_OWNER_AND_GROUP("the owner is %s, the group is %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HINT_ACTUAL_POSIX_PERMISSIONS("the permissions are %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HINT_ACTUAL_ACL_PERMISSIONS("the Access Control List is:"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FAILURE_DUE_TO_PARENT("failure at parent path"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FAILURE_DUE_TO_ACCESS_DENIED("access was denied"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FAILURE_DUE_TO_ACCESS_EXCEPTION("access threw a %s:"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FAILURE_DUE_TO_WRONG_FILE_TYPE("was %s instead of %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    FAILURE_DUE_TO_LINK_LOOP("found a symbolic link loop: %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HINT_CLOSEST_EXISTING_PARENT_DIRECTORY("the closest existing parent directory is %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HINT_FOLLOWED_SYMBOLIC_LINK("followed the symbolic link %s to %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HAS_SAME_TEXTUAL_CONTENT("has same textual content with encoding %s, %s"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    HAS_SAME_BINARY_CONTENT("has same binary content"),

    /** @since 1.3.0 (but was in DescriptionPathAssertion before) */
    RELATIVE_PATH("a relative path"),
}
