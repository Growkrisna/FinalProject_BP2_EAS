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
