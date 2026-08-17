# Project Client Z — Finance Mobile App

Aplikasi Android berbasis Kotlin untuk membantu pengguna mencatat pemasukan, pengeluaran, dan catatan keuangan secara lokal. Aplikasi ini dibuat sebagai proyek pembelajaran pengembangan Android dengan arsitektur berbasis Room Database, ViewModel, dan Kotlin Coroutines.

## Fitur Utama

* Menambahkan, mengubah, menghapus, dan mencari data pemasukan.
* Menambahkan, mengubah, dan menghapus data pengeluaran.
* Menghitung total pemasukan dan pengeluaran.
* Membuat, mencari, dan menghapus catatan.
* Menyimpan data keuangan secara lokal menggunakan Room Database.
* Menampilkan artikel singkat mengenai pengelolaan keuangan.
* Menampilkan halaman biodata.
* Menghitung estimasi kalori dari aktivitas sit-up, pull-up, dan berlari.
* Menyimpan data aktivitas menggunakan SharedPreferences.
* Navigasi antarlayar menggunakan Android Navigation Component.

## Teknologi yang Digunakan

* Kotlin
* Android SDK 34
* Minimum Android SDK 26
* JDK 17
* Gradle 8.0
* Android Gradle Plugin 8.1.0
* AndroidX
* Material Components
* Room Database
* ViewModel dan LiveData
* Kotlin Coroutines
* View Binding
* Navigation Component
* RecyclerView
* Coil dan Glide
* MPAndroidChart

## Struktur Proyek

```text
project-clien-z/
├── app/
│   ├── src/main/java/com/example/projectzulfa/
│   │   ├── Adapter/       # Adapter untuk RecyclerView
│   │   ├── Dao/           # Data Access Object Room
│   │   ├── Database/      # Konfigurasi database lokal
│   │   ├── entitas/       # Entitas data aplikasi
│   │   ├── model/         # ViewModel dan model data
│   │   └── UI/            # Fragment dan tampilan tambahan
│   └── src/main/res/
│       ├── drawable/      # Gambar dan ikon
│       ├── layout/        # Layout antarmuka
│       ├── menu/          # Menu navigasi
│       ├── navigation/    # Navigation graph
│       └── values/        # Warna, tema, dan string
├── gradle/wrapper/
├── build.gradle.kts
├── gradle.properties
└── settings.gradle.kts
```

## Persyaratan

Sebelum menjalankan proyek, pastikan perangkat telah memiliki:

* Android Studio
* JDK 17
* Android SDK 34
* Git
* Emulator atau perangkat Android minimal versi Android 8.0 (API 26)

## Cara Menjalankan

1. Clone repository:

```bash
git clone https://github.com/USERNAME/project-clien-z.git
```

2. Masuk ke folder proyek:

```bash
cd project-clien-z
```

3. Buka folder proyek menggunakan Android Studio.
4. Tunggu proses Gradle Sync selesai.
5. Pilih emulator atau perangkat Android.
6. Tekan tombol **Run**.

Ganti `USERNAME` pada alamat clone dengan username GitHub pemilik repository.

## Build melalui Terminal

Windows:

```powershell
.\gradlew.bat assembleDebug
```

Linux atau macOS:

```bash
./gradlew assembleDebug
```

APK debug akan dibuat di:

```text
app/build/outputs/apk/debug/
```

## Pengujian

Menjalankan unit test:

```bash
./gradlew test
```

Menjalankan instrumented test menggunakan emulator atau perangkat Android:

```bash
./gradlew connectedAndroidTest
```

## Penyimpanan Data

Aplikasi menggunakan penyimpanan lokal:

* Room Database untuk pemasukan, pengeluaran, dan catatan.
* SharedPreferences untuk menyimpan data aktivitas sederhana.

Data tidak dikirim ke server atau layanan API eksternal.



