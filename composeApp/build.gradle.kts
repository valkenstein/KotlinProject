import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
    id("com.google.devtools.ksp") version "2.0.0-1.0.22"
    id("de.jensklingenberg.ktorfit") version "2.0.0"
    //id("dev.icerock.mobile.multiplatform-resources")
}

configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
    version = libs.versions.ktorfit.asProvider().get()
}
kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
//            export("dev.icerock.moko:resources:0.24.1")
//            export("dev.icerock.moko:graphics:0.9.0")
        }
    }
//    multiplatformResources {
//        resourcesPackage.set("org.example.library") // required
//        resourcesClassName.set("SharedRes") // optional, default MR
//        resourcesVisibility.set(MRVisibility.Internal) // optional, default Public
//        iosBaseLocalizationRegion.set("en") // optional, default "en"
//        iosMinimalDeploymentTarget.set("11.0") // optional, default "9.0"
//    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.core)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            //mvvm
            implementation(libs.mvvm.core)
            implementation(libs.mvvm.compose)
            implementation(libs.mvvm.flow)
            implementation(libs.mvvm.flow.compose)

            //navigation compose
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.7.0-alpha07")
            //voyager
            // Navigator
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transitions)

            //lottie
            implementation(libs.compottie)

            /**
             * Ktor & Ktorfit
             */
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.jensklingenberg.ktorfit)
            //koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)
            //ksp("de.jensklingenberg.ktorfit:ktorfit-ksp:1.0.0") // версия KSP процессора Ktorfit


//            implementation(libs.navigation.compose)
//            implementation(libs.kotlinx.serialization.json)


        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
//multiplatformResources {
//    resourcesPackage.set("org.example.project") // required
//    resourcesClassName.set("SharedRes") // optional, default MR
//    resourcesVisibility.set(MRVisibility.Internal) // optional, default Public
//    iosBaseLocalizationRegion.set("en") // optional, default "en"
//    iosMinimalDeploymentTarget.set("11.0") // optional, default "9.0"
//}
dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.ui.tooling.preview.desktop)
}

