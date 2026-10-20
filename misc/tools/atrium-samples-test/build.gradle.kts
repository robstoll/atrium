plugins {
    id("build-logic.gradle-conventions")
}

//TODO 2.0.0 try to include ...-kotlin-test again once we updated kotlin
// currently the backend compiler of kotlin crashes
val samples = listOf("junit5", "kotest", "testng")//, "js-kotlin-test")//, "multiplatform-kotlin-test")


tasks.register("build") {
    samples.forEach { projectName ->
        dependsOn(gradle.includedBuild(projectName).task(":build"))
    }
}

tasks.register("clean") {
    samples.forEach { projectName ->
        dependsOn(gradle.includedBuild(projectName).task(":clean"))
    }
}

tasks.register("cleanAtrium") {
    dependsOn(gradle.includedBuild("atrium").task(":clean"))
}
