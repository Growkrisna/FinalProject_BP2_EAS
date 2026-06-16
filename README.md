## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Progress Anggota 1 - Final Project UAS BP2

## Identitas
- **Nama:** KRISNA
- **NPM:** 088
- **Peran:** Anggota 1 (Manajemen Data & Queue / Antrian)

---

## ✅ Tugas yang Telah Diselesaikan

### 1. Class `Produk.java`
- Menyimpan data barang yang dijual (nama produk, harga)
- Memiliki method `getNamaProduk()`, `getHarga()`, `setHarga()`
- Memiliki field `next` untuk mendukung **single linked list** di dalam transaksi

### 2. Class `Transaksi.java`
- Menyimpan data 1 kali pembelian
- Atribut:
  - `idTransaksi` (int) - kode unik transaksi
  - `idPembeli` (int) - ID pembeli (atau nama nanti bisa disesuaikan)
  - `status` (int) - 0 = belum diproses, 1 = sudah diproses
  - `totalHarga` (double) - total belanja
  - `head`, `tail` (Produk) - untuk linked list daftar barang yang dibeli
  - `next` (Transaksi) - untuk single linked list di queue utama
- Method:
  - `addProduk()` - menambah barang ke keranjang belanja
  - `lihatBelanja()` - menampilkan daftar barang yang dibeli
  - `hapusSemua()` - mengosongkan keranjang
  - `setStatus()` / `getStatus()` - mengubah/membaca status transaksi
  - `getIdTransaksi()` - mendapatkan kode transaksi dalam format String
  - `tampil()` - menampilkan ringkasan transaksi

### 3. Class `Node.java`
- Menyimpan node untuk queue utama
- Atribut:
  - `data` (Transaksi)
  - `next` (Node)

### 4. Class `Queue.java` (Antrian Utama Toko)
- Mengelola antrian transaksi dengan prinsip **FIFO** (First In First Out)
- Atribut:
  - `front` (Node) - node terdepan dalam antrian
  - `rear` (Node) - node terbelakang dalam antrian
- Method:
  - `enqueue(Transaksi)` - menambahkan transaksi baru ke belakang antrian
  - `dequeue()` - memproses transaksi paling depan (status berubah jadi 1, lalu dihapus dari antrian)
  - `showUnprocessed()` - menampilkan semua transaksi yang masih berstatus 0 (belum diproses)
  - `showAll()` - menampilkan semua transaksi (untuk debugging)
  - `hasUnprocessed()` - mengecek apakah masih ada transaksi yang belum diproses

---

## 🔗 Antarmuka yang Disiapkan untuk Anggota 2 & 3

| Untuk Anggota 2 (Menu Pembeli & Member) | Untuk Anggota 3 (Menu Admin & Pemilik) |
|-----------------------------------------|-----------------------------------------|
| 5 objek `Produk` (roti tawar, roti cokelat, roti keju, roti sosis, donat) | Queue `antrianUtama` yang sudah berisi transaksi |
| Constructor `Transaksi(int idTransaksi, int idPembeli)` | Method `dequeue()` untuk memproses antrian |
| Method `addProduk()` untuk menambah barang ke keranjang | Method `showUnprocessed()` untuk melihat transaksi belum diproses |
| Method `lihatBelanja()` untuk menampilkan keranjang | Method `hasUnprocessed()` untuk pengecekan |
| Method `hapusSemua()` untuk mengosongkan keranjang | Method `getStatus()` / `setStatus()` untuk update status transaksi |

---

## 📂 Struktur File Saat Ini

├── Produk.java ✅ Selesai
├── Transaksi.java ✅ Selesai
├── Node.java ✅ Selesai
├── Queue.java ✅ Selesai
├── App.java ❌ Belum dibuat (kalian buat dan untuk integrasi)
├── admin.java ❌ Belum dibuat (tugas anggota 3)
├── member.java ❌ Belum dibuat (tugas anggota 2)
├── pemilik.java ❌ Belum dibuat (tugas anggota 3)
└── laporan.java ❌ Belum dibuat (tugas anggota 3)

# Aplikasi Pengelolaan Order Penjualan Online — Toko Roti UMKM
**Final Project — Bahasa Pemrograman 2**

Aplikasi berbasis Java untuk mengelola transaksi penjualan online pada sebuah toko roti UMKM. Transaksi dikelola menggunakan **Single Linked List Queue (FIFO)** — transaksi yang masuk lebih awal akan diproses lebih dulu.

---

## Struktur File

```
src/
├── App.java          # Class utama / entry point aplikasi
├── Produk.java       # Class data barang (nama, harga)
├── Transaksi.java    # Class data transaksi (berisi linked list produk)
├── Node.java         # NodeProduk dan NodeTransaksi untuk linked list
├── Queue.java        # Linked list queue antrian transaksi utama toko
└── laporan.java      # (placeholder, belum diimplementasi)
```

---

## Desain Class

### `Produk`
Menyimpan data satu jenis barang yang dijual.
- Field: `namaProduk`, `harga`, `next` (pointer linked list)
- Method: `getNama()`, `getNamaProduk()`, `getHarga()`, `setHarga()`, `getInfoProduk()`

### `Transaksi`
Menyimpan data satu sesi pembelian. Di dalamnya terdapat **single linked list produk** (`headBelanja` - `tailBelanja`) untuk menyimpan daftar barang yang dibeli.
- Field: `idTransaksi`, `namaPembeli`, `memberIndex`, `status`, `totalHarga`, `headBelanja`, `tailBelanja`, `next`
- Method: `tambahProduk()`, `lihatKeranjang()`, `hapusSemua()`, `applyDiskon()`, `tampil()`, getter & setter

### `Node` (berisi 3 class dalam 1 file)
- `Node` — node lama (tidak dipakai aktif, digantikan NodeTransaksi)
- `NodeProduk` — node untuk linked list produk di dalam Transaksi
- `NodeTransaksi` — node untuk linked list queue di Queue

### `Queue`
Antrian transaksi utama toko menggunakan **single linked list dengan pointer `front` dan `rear`**.
- `enqueue(Transaksi)` — tambah transaksi ke belakang antrian
- `dequeue()` — tandai transaksi pertama yang belum diproses menjadi status 1
- `tampilkanBelumDiproses()` — tampilkan semua transaksi berstatus 0
- `hasUnprocessed()` — cek apakah masih ada transaksi belum diproses
- `getFront()` — ambil node terdepan untuk keperluan iterasi

### `App`
Class utama yang menjalankan seluruh alur program. Berisi menu untuk 4 level pengguna.

---

## Akun dan Password

| Level    | ID / Password                |
|----------|------------------------------|
| Member 1 | ID: `M001` / Pass: `pass123` |
| Member 2 | ID: `M002` / Pass: `pass456` |
| Member 3 | ID: `M003` / Pass: `pass789` |
| Admin    | Password: `admin123`         |
| Pemilik  | Password: `owner123`         |

---

## Fitur per Level Pengguna

### Pembeli
- Memilih barang dan jumlah yang ingin dibeli
- Lihat keranjang belanja beserta total biaya
- Hapus semua barang dari keranjang
- Selesai & bayar — transaksi masuk ke antrian toko
- Bisa login sebagai member di akhir sesi untuk mendapat diskon 5%

### Member
- Login dengan ID dan password di awal
- Diskon 5% langsung diterapkan ke harga per item saat ditambahkan
- Fitur sama dengan Pembeli (Tambah, Lihat, Hapus, Selesai)
- Tambahan: Ubah Password (dengan konfirmasi password lama dan baru)

### Admin
- Login dengan password
- Tampilkan semua transaksi yang belum diproses beserta jumlahnya
- Proses 1 transaksi (FIFO — paling awal masuk diproses dulu)
- Proses semua transaksi sekaligus
- Total belanja member diperbarui otomatis setiap transaksi mereka diproses

### Pemilik
- Login dengan password
- Total nilai order yang sudah diproses (jumlah transaksi + total nilai)
- Total nilai order yang belum diproses
- Ubah harga barang (pilih nama barang lalu masukkan harga baru)
- Laporan penjualan per jenis barang + total pendapatan keseluruhan
- Laporan total belanja per member
- Grafik penjualan per barang dengan karakter X (skala Rp 10.000 per X)

---

## Cara Menjalankan

**Menggunakan VS Code:**
1. Buka folder project di VS Code
2. Pastikan Java Extension Pack sudah terinstall
3. Buka `src/App.java`, klik Run atau tekan F5

**Menggunakan terminal:**
```bash
# Kompilasi
javac -d bin src/*.java

# Jalankan
java -cp bin App
```

---

## Catatan

- `laporan.java` masih kosong (placeholder), semua logika laporan sudah terintegrasi langsung di `App.java`
- `Node.java` berisi 3 class sekaligus; yang aktif digunakan adalah `NodeProduk` dan `NodeTransaksi`
- Kode transaksi menggunakan format `MMDD-NNNN` (tanggal statis `1206` + nomor urut 4 digit)
- Data member (ID, password, total belanja) disimpan dalam array paralel di `App.java`