@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.kotlinAndroid)
	alias(libs.plugins.ksp)
	alias(libs.plugins.junit)
}

android {
	namespace = "com.elmirov.tinkofftesttask"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.elmirov.tinkofftesttask"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}

	buildFeatures {
		viewBinding = true
	}
}

dependencies {

	implementation(libs.core.ktx)
	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.constraintlayout)

	implementation(libs.cicerone)

	implementation(libs.dagger.core)
	ksp(libs.dagger.compiler)

	implementation(libs.room.core)
	ksp(libs.room.compiler)

	implementation(libs.coil)

	implementation(libs.paging)

	implementation(libs.bundles.lifecycle)
	implementation(libs.bundles.retrofit)
	implementation(libs.bundles.coroutines)
	testImplementation(libs.bundles.unit.tests)
}