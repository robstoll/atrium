plugins {
    id("build-logic.gradle-conventions")
}

val samples = listOf("junit5", "kotest", "testng", "js-kotlin-test", "js-kotest", "multiplatform-kotlin-test")


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
