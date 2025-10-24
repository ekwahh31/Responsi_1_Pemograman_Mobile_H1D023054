# Cara Mengatasi Error 400 - API Token

## Penyebab Error 400
Error 400 (Bad Request) terjadi karena:
1. **API Token tidak valid atau sudah expired**
2. Token belum diganti dengan token Anda sendiri
3. API Football Data memerlukan token yang terdaftar

## Solusi: Dapatkan API Token Anda Sendiri

### Langkah 1: Registrasi di Football Data API
1. Buka website: **https://www.football-data.org/client/register**
2. Isi form registrasi:
   - Email
   - Password
   - Nama
   - Tujuan penggunaan (pilih: Education/Learning)
3. Klik **Register**
4. Cek email untuk verifikasi akun

### Langkah 2: Dapatkan API Token
1. Login ke: **https://www.football-data.org/client/login**
2. Setelah login, Anda akan melihat **API Token** di dashboard
3. Copy token tersebut (format: string panjang seperti `abc123def456...`)

### Langkah 3: Ganti Token di Aplikasi
1. Buka file: `app/src/main/java/com/example/responsi1mobileh1d023054/network/ApiConfig.kt`
2. Cari baris:
   ```kotlin
   private const val API_TOKEN = "YOUR_API_TOKEN_HERE"
   ```
3. Ganti `YOUR_API_TOKEN_HERE` dengan token Anda:
   ```kotlin
   private const val API_TOKEN = "token_anda_dari_website"
   ```
4. Save file
5. Rebuild aplikasi (Build > Rebuild Project)
6. Run aplikasi

## Contoh Token yang Valid
```kotlin
private const val API_TOKEN = "1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p"
```

## Free Tier Limitations
API gratis memiliki batasan:
- **10 requests per menit**
- Akses ke kompetisi tertentu saja
- Tim populer seperti Ajax (ID: 17) biasanya tersedia

## Verifikasi Token
Setelah mendapatkan token, test dengan cURL:
```bash
curl -X GET "https://api.football-data.org/v4/teams/17" \
  -H "X-Auth-Token: YOUR_TOKEN_HERE"
```

Jika berhasil, Anda akan melihat data JSON tim Ajax.

## Troubleshooting

### Masih Error 400?
- Pastikan token disalin dengan benar (tidak ada spasi)
- Token case-sensitive
- Rebuild project setelah mengubah token

### Error 429 (Too Many Requests)?
- Tunggu 1 menit
- Free tier limit: 10 requests/menit

### Error 403 (Forbidden)?
- Token tidak valid
- Akun belum terverifikasi
- Dapatkan token baru

### Tidak bisa registrasi?
Alternatif: Gunakan tim ID lain yang tersedia di free tier:
- Manchester United: 66
- Chelsea: 61
- Arsenal: 57

Ubah di HeadCoach.kt dan TeamSquadFragment.kt:
```kotlin
val teamId = 66 // Ganti dari 17 ke tim lain
```

## Catatan Penting
- **JANGAN** commit/push API Token ke Git/GitHub (sensitif)
- Token bersifat personal
- Ganti token jika terexpose

## Logcat Debug
Untuk melihat error detail, cek Logcat:
1. Di Android Studio, buka **Logcat**
2. Filter dengan tag: `HeadCoach` atau `TeamSquadFragment`
3. Lihat error message lengkap
