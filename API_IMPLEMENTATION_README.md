# Implementasi API Football Data - Ajax Amsterdam

## Overview
Implementasi ini mengambil data pemain dan pelatih Ajax Amsterdam (Team ID: 17) dari API Football Data menggunakan arsitektur Model-Network-UI.

## Struktur Implementasi

### 1. Model Layer (`model/TeamResponse.kt`)
Data classes untuk response API:
- `TeamResponse`: Data lengkap tim termasuk coach dan squad
- `Coach`: Informasi pelatih kepala
- `Squad`: Informasi pemain
- `Contract`: Detail kontrak pelatih

### 2. Network Layer
#### `network/ApiConfig.kt`
- Base URL: `https://api.football-data.org/v4/`
- API Token: `1e87e50c1fe04bd1bd90d49c1ca63e11`
- Token dikirim via header: `X-Auth-Token`
- Menggunakan OkHttp Logging Interceptor untuk debugging

#### `network/ApiService.kt`
Endpoint:
- `GET /teams/{id}`: Mengambil data tim berdasarkan ID

### 3. UI Layer

#### `adapter/PlayerAdapter.kt`
RecyclerView Adapter untuk menampilkan list pemain:
- Menampilkan nama dan nationality pemain
- OnClick menampilkan BottomSheet dengan detail lengkap
- BottomSheet menampilkan: nama, tanggal lahir, nationality, posisi

#### `fragment/TeamSquadFragment.kt`
Fragment untuk halaman pemain:
- RecyclerView dengan LinearLayoutManager
- Fetch data dari API (Team ID: 17)
- Filter hanya pemain (yang memiliki posisi)
- Error handling dengan Toast

#### `TeamSquad.kt` (Activity)
- Load TeamSquadFragment ke dalam fragment_container
- Handle fragment transaction

#### `HeadCoach.kt` (Activity)
- Fetch data coach dari API yang sama
- Menampilkan: nama, tanggal lahir, nationality
- Gambar coach menggunakan resource lokal (sesuai hint)

## Dependencies yang Ditambahkan
```kotlin
// Retrofit for API calls
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp Logging Interceptor
implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

// Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

// Fragment KTX
implementation("androidx.fragment:fragment-ktx:1.6.2")
```

## Permissions
AndroidManifest.xml:
```xml
<uses-permission android:name="android.permission.INTERNET" />
android:usesCleartextTraffic="true"
```

## Flow Aplikasi

### Halaman Pemain (TeamSquad Activity)
1. Activity dibuka
2. Load TeamSquadFragment
3. Fragment fetch data dari API `/teams/17`
4. Filter squad yang memiliki posisi (pemain)
5. Tampilkan di RecyclerView
6. Klik item → tampilkan BottomSheet dengan detail

### Halaman Pelatih (HeadCoach Activity)
1. Activity dibuka
2. Fetch data dari API `/teams/17`
3. Ambil data coach dari response
4. Tampilkan nama, DOB, nationality
5. Gambar dari resource lokal

## API Response Structure
```json
{
  "id": 17,
  "name": "AFC Ajax",
  "coach": {
    "id": 123,
    "name": "Coach Name",
    "dateOfBirth": "1970-01-01",
    "nationality": "Netherlands"
  },
  "squad": [
    {
      "id": 456,
      "name": "Player Name",
      "position": "Midfielder",
      "dateOfBirth": "1995-01-01",
      "nationality": "Netherlands"
    }
  ]
}
```

## Catatan Penting
- Satu API request untuk mendapatkan data coach dan pemain
- Token API dipakai di header request dengan key `X-Auth-Token`
- Data coach dan pemain dari API (kecuali gambar coach)
- BottomSheet untuk menampilkan detail pemain
- Error handling dengan Toast dan Log
