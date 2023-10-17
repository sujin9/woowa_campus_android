// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.openapi.generator") version "7.0.1" apply true
}

buildscript {
    repositories {
        maven(url = "https://repo1.maven.org/maven2")
    }
}

tasks.register(
    "generateClient",
    org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class,
) {
    generatorName.set("kotlin")
    // .yaml 파일이 위치하는 경로
    inputSpec.set("$rootDir/app/src/test/java/woowacourse/campus/TestApi.yaml")
    // generate된 코드가 위치할 경로
    outputDir.set("$rootDir/network")
    // genertate된 코드가 가질 상위 경로
    packageName.set("")
    apiPackage.set("api")
    modelPackage.set("model")
    invokerPackage.set("invoker")
    // 설정하기
    configOptions.set(
        mapOf(
            "library" to "jvm-ktor",
            "dateLibrary" to "java8",
            "omitGradleWrapper" to "true",
            "sourceFolder" to "src/main/java",
            "useCoroutines" to "true",
            "serializationLibrary" to "gson"
        ),
    )
}
