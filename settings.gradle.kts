import java.util.Properties

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}


val user = System.getenv("PACKAGES_USERNAME") ?: findInGradleProperties("github.user")
val token = System.getenv("PACKAGES_TOKEN") ?: findInGradleProperties("github.token")

fun findInGradleProperties(env: String): String? {
    val gradlePropertiesFile = File(System.getProperty("user.home"), ".gradle/gradle.properties")
    return if (gradlePropertiesFile.exists()) {
        val properties = Properties()
        gradlePropertiesFile.inputStream().use { input -> properties.load(input) }
        properties.getProperty(env)
    } else {
        null
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()

        maven {
            url = uri("https://maven.pkg.github.com/ClipMX/*")
            credentials {
                username = user
                password = token
            }
        }

        versionCatalogs {
            create("clipLibs") {
                from("com.payclip.blaze:versions-catalog:0.6.0")
            }
        }
    }


}

rootProject.name = "EMC SDK"
include(":app")
include(":embedded-connection-sdk")
