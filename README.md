# PRAKTIKUM PEMOGRAMAN MOBILE
EKA BINTANG WICAKSONO<br>
H1D023054<br>
SHIFT KRS B<br>
SHIFT BARU A<br>
<br>
# DEMO APLIKASI


https://github.com/user-attachments/assets/483a814e-495e-4057-bb1a-7d3e68a3abb6



# PENJELASAN ALUR DATA
Saat aplikasi dibuka, page HeadCoach dan TeamSquad akan memanggil API melalui ApiConfig.kt dan ApiService.kt. Pada ApiConfig.kt terdapat Retrofit dan interceptor yang akan menambahkan token APi yang sudah di inputkan ke Header untuk setiap request.

Request akan dikirim ke endpoint /teams/17 untuk mendapatkan data tim SC Freiburg. Response JSONnya akan langsung di ubah menjadi objek kotlin sesuai dengan model yang ada pada TeamResponse.kt.

Di HeadCoach.kt, data pelatih akan langsung diambil dari response lalu ditampilin ke TextView nama, tanggal lahir, dan warga negara.

Di TeamSquadFragment.kt, data pemain akan di filter sesuai dengan posisinya yaitu:

Goalkeeper berwarna kuning<br>
Defender berwarna biru<br>
Midfielder berwarna hijau<br>
Forward berwarna merah

terus dikirim ke PlayerAdapter.kt untuk menentukan warna dari cardnya. Saat salah satu card pemain di klik, adapter akan memanggil showPlayerBottomSheet yang inflate layout player_info.xml untuk menunjukan detail dari pemainnya.
