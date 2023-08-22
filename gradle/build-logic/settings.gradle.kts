rootProject.name = "build-logic"

pluginManagement {
    includeBuild("../build-logic-conventions")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("basics")
include("build-parameters")
include("dev")
include("publishing")
include("root-build")
