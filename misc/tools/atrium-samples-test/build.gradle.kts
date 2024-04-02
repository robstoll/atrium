plugins {
    id("build-logic.gradle-conventions")
}

//TODO 1.3.0 include ...-kotlin-test again once we use IR for JS also in samples
val samples = listOf("junit5", "kotest", "testng")//, "js-kotlin-test", "multiplatform-kotlin-test")

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
