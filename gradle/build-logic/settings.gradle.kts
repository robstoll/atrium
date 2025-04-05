rootProject.name = "build-logic"

pluginManagement {
    includeBuild("../build-logic-conventions")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        mavenLocal()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../libs.versions.toml"))
        }
    }
    versionCatalogs {
        create("buildLibs") {
            from(files("../buildLibs.versions.toml"))
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("basics")
include("build-parameters")
include("compiler")
include("dev")
include("publishing")
include("root-build")
