import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Queue antrian = new Queue();

        // Buat 5 objek produk terpisah (sesuai spesifikasi tugas)
        Produk produk1 = new Produk("Roti Tawar", 20000);
        Produk produk2 = new Produk("Roti Cokelat", 25000);
        Produk produk3 = new Produk("Roti Keju", 30000);
        Produk produk4 = new Produk("Roti Sosis", 28000);
        Produk produk5 = new Produk("Donat", 15000);

        // Untuk kemudahan iterasi pada menu, kumpulkan referensi (bukan definisi baru)
        Produk[] daftarProduk = { produk1, produk2, produk3, produk4, produk5 };

        String[] idMember = {"M001", "M002", "M003"};
        String[] passwordMember = {"pass123", "pass456", "pass789"};
        double[] totalBelanjaMember = {0, 0, 0};

        int counterTransaksi = 1;
        String tanggal = "1206";

        String passwordAdmin = "admin123";
        String passwordPemilik = "owner123";

        System.out.println("TOKO ROTI XYZ");
        System.out.println("Sistem Order Penjualan Online");
        
        boolean running = true;
        while (running) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Pembeli");
            System.out.println("2. Member");
            System.out.println("3. Admin");
            System.out.println("4. Pemilik");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            int pilihanUtama = scan.nextInt();
            scan.nextLine();
            
            switch (pilihanUtama) {
                case 1:
                    System.out.print("\nMasukkan nama pembeli: ");
                    String namaPembeli = scan.nextLine();

                    String kodeTransaksi = tanggal + "-" + String.format("%04d", counterTransaksi++);
                    Transaksi transaksi = new Transaksi(kodeTransaksi, namaPembeli);
                    
                    boolean pembeli = true;
                    while (pembeli) {
                        System.out.println("\nMenu Pembeli");
                        for (int i = 0; i < daftarProduk.length; i++) {
                            System.out.println((i + 1) + ". " + daftarProduk[i].getNama() + " (Rp " + daftarProduk[i].getHarga() + ")");
                        }
                        System.out.println((daftarProduk.length + 1) + ". Lihat Keranjang");
                        System.out.println((daftarProduk.length + 2) + ". Hapus Semua");
                        System.out.println((daftarProduk.length + 3) + ". Selesai & Bayar");
                        System.out.print("Pilih: ");
                        int pilihanPembeli = scan.nextInt();
                        scan.nextLine();
                        
                        if (pilihanPembeli >= 1 && pilihanPembeli <= daftarProduk.length) {
                            System.out.print("Jumlah: ");
                            int jumlah = scan.nextInt();
                            scan.nextLine();

                            if (jumlah < 1) {
                                System.out.println("Jumlah harus minimal 1.");
                            } else {
                                for (int i = 0; i < jumlah; i++) {
                                    transaksi.tambahProduk(daftarProduk[pilihanPembeli - 1]);
                                }
                            }
                        } else if (pilihanPembeli == daftarProduk.length + 1) {
                            transaksi.lihatKeranjang();
                        } else if (pilihanPembeli == daftarProduk.length + 2) {
                            transaksi.hapusSemua();
                        } else if (pilihanPembeli == daftarProduk.length + 3) {
                            System.out.println("\nSelesai berbelanja!");
                            transaksi.lihatKeranjang();

                            System.out.print("\nApakah Anda memiliki kartu member? (y/n): ");
                            String jawabMember = scan.nextLine();
                            
                            int indexMember = -1;
                            if (jawabMember.equalsIgnoreCase("y")) {
                                System.out.println("\nLogin Member");
                                System.out.print("ID Member : ");
                                String id = scan.nextLine();
                                System.out.print("Password  : ");
                                String pass = scan.nextLine();

                                for (int i = 0; i < idMember.length; i++) {
                                    if (idMember[i].equals(id) && passwordMember[i].equals(pass)) {
                                        indexMember = i;
                                        break;
                                    }
                                }
                                
                                if (indexMember != -1) {
                                    System.out.println("\nLogin berhasil. Selamat datang " + idMember[indexMember]);
                                    transaksi.applyDiskon(5);
                                    transaksi.setMemberIndex(indexMember);
                                } else {
                                    System.out.println("\nLogin gagal. ID atau password salah.");
                                    System.out.println("Transaksi tetap diproses dengan harga normal.");
                                }
                            } else {
                                System.out.println("Transaksi diproses sebagai pembeli biasa.");
                            }
                            
                            antrian.enqueue(transaksi);
                            pembeli = false;
                        } else {
                            System.out.println("❌ Pilihan salah!");
                        }
                    }
                    break;

                        case 2:
                            System.out.println("\nLogin Member");
                            System.out.print("ID Member : ");
                            String mid = scan.nextLine();
                            System.out.print("Password  : ");
                            String mpass = scan.nextLine();
                            int idxMember = -1;
                            for (int i = 0; i < idMember.length; i++) {
                                if (idMember[i].equals(mid) && passwordMember[i].equals(mpass)) {
                                    idxMember = i;
                                    break;
                                }
                            }
                            if (idxMember == -1) {
                                System.out.println("Login member gagal. Kembali ke menu utama.");
                                break;
                            }

                            System.out.println("Login member berhasil. Selamat datang " + idMember[idxMember]);
                            String kodeTransaksiM = tanggal + "-" + String.format("%04d", counterTransaksi++);
                            Transaksi transaksiM = new Transaksi(kodeTransaksiM, idMember[idxMember]);
                            transaksiM.setMemberIndex(idxMember);

                            boolean memberOn = true;
                            while (memberOn) {
                                System.out.println("\nMenu Member");
                                for (int i = 0; i < daftarProduk.length; i++) {
                                    System.out.println((i + 1) + ". " + daftarProduk[i].getNama() + " (Rp " + daftarProduk[i].getHarga() + ")");
                                }
                                System.out.println((daftarProduk.length + 1) + ". Lihat Keranjang");
                                System.out.println((daftarProduk.length + 2) + ". Hapus Semua");
                                System.out.println((daftarProduk.length + 3) + ". Ubah Password");
                                System.out.println((daftarProduk.length + 4) + ". Selesai & Bayar");
                                System.out.print("Pilih: ");
                                int pm = scan.nextInt();
                                scan.nextLine();

                                if (pm >= 1 && pm <= daftarProduk.length) {
                                    System.out.print("Jumlah: ");
                                    int jumlah = scan.nextInt();
                                    scan.nextLine();
                                    if (jumlah < 1) {
                                        System.out.println("Jumlah harus minimal 1.");
                                    } else {
                                        // Terapkan diskon 5% per item saat ditambahkan
                                        for (int k = 0; k < jumlah; k++) {
                                            Produk asli = daftarProduk[pm - 1];
                                            double hargaDiskon = asli.getHarga() * 0.95;
                                            Produk diskonProduk = new Produk(asli.getNama(), hargaDiskon);
                                            transaksiM.tambahProduk(diskonProduk);
                                        }
                                    }
                                } else if (pm == daftarProduk.length + 1) {
                                    transaksiM.lihatKeranjang();
                                } else if (pm == daftarProduk.length + 2) {
                                    transaksiM.hapusSemua();
                                } else if (pm == daftarProduk.length + 3) {
                                    // Ubah password
                                    System.out.print("Masukkan password lama: ");
                                    String lama = scan.nextLine();
                                    if (!passwordMember[idxMember].equals(lama)) {
                                        System.out.println("Password lama salah.");
                                    } else {
                                        System.out.print("Masukkan password baru: ");
                                        String baru1 = scan.nextLine();
                                        System.out.print("Konfirmasi password baru: ");
                                        String baru2 = scan.nextLine();
                                        if (!baru1.equals(baru2)) {
                                            System.out.println("Konfirmasi tidak cocok.");
                                        } else {
                                            passwordMember[idxMember] = baru1;
                                            System.out.println("Password berhasil diubah.");
                                        }
                                    }
                                } else if (pm == daftarProduk.length + 4) {
                                    System.out.println("\nSelesai berbelanja!");
                                    transaksiM.lihatKeranjang();
                                    antrian.enqueue(transaksiM);
                                    memberOn = false;
                                } else {
                                    System.out.println("Pilihan salah.");
                                }
                            }
                            break;
                 case 3:
                    System.out.print("\nMasukkan password admin: ");
                    String passAdmin = scan.nextLine();
                    
                    if (passwordAdmin.equals(passAdmin)) {
                        System.out.println("Login sebagai admin berhasil.");
                        boolean admin = true;
                        while (admin) {
                            System.out.println("\nMenu Admin");
                            System.out.println("1. Tampilkan transaksi belum diproses");
                            System.out.println("2. Proses 1 transaksi");
                            System.out.println("3. Proses semua transaksi");
                            System.out.println("4. Kembali ke menu utama");
                            System.out.print("Pilih: ");
                            int pilihanAdmin = scan.nextInt();
                            scan.nextLine();
                            
                            switch (pilihanAdmin) {
                                case 1:
                                    antrian.tampilkanBelumDiproses();
                                    break;
                                case 2:
                                    if (antrian.hasUnprocessed()) {
                                        Transaksi diproses = antrian.dequeue();
                                        if (diproses != null && diproses.getMemberIndex() >= 0) {
                                            totalBelanjaMember[diproses.getMemberIndex()] += diproses.getTotalHarga();
                                        }
                                    } else {
                                        System.out.println("❌ Tidak ada transaksi yang perlu diproses.");
                                    }
                                    break;
                                case 3:
                                    int diproses = 0;
                                    while (antrian.hasUnprocessed()) {
                                        Transaksi selesai = antrian.dequeue();
                                        if (selesai != null && selesai.getMemberIndex() >= 0) {
                                            totalBelanjaMember[selesai.getMemberIndex()] += selesai.getTotalHarga();
                                        }
                                        diproses++;
                                    }
                                    if (diproses == 0) {
                                        System.out.println("Tidak ada transaksi yang perlu diproses.");
                                    } else {
                                        System.out.println(diproses + " transaksi telah diproses.");
                                    }
                                    break;
                                case 4:
                                    System.out.println("Kembali ke menu utama...");
                                    admin = false;
                                    break;
                                default:
                                    System.out.println("❌ Pilihan salah!");
                            }
                        }
                    } else {
                        System.out.println("❌ Password salah!");
                    }
                    break;

                case 4:
                    System.out.print("\nMasukkan password pemilik: ");
                    String passPemilik = scan.nextLine();
                    
                    if (passwordPemilik.equals(passPemilik)) {
                        System.out.println("Login sebagai pemilik berhasil.");
                        boolean pemilik = true;
                        while (pemilik) {
                            System.out.println("\nMenu Pemilik");
                            System.out.println("1. Total nilai order sudah diproses");
                            System.out.println("2. Total nilai order belum diproses");
                            System.out.println("3. Ubah harga barang");
                            System.out.println("4. Laporan penjualan per jenis barang");
                            System.out.println("5. Laporan total belanja member");
                            System.out.println("6. Grafik penjualan");
                            System.out.println("7. Kembali ke menu utama");
                            System.out.print("Pilih: ");
                            int pilihanPemilik = scan.nextInt();
                            scan.nextLine();
                            
                            switch (pilihanPemilik) {
                                case 1:
                                    // Total sudah diproses
                                    {
                                        double total = 0;
                                        int count = 0;
                                        NodeTransaksi current = antrian.getFront();
                                        while (current != null) {
                                            if (current.data.getStatus() == 1) {
                                                total += current.data.getTotalHarga();
                                                count++;
                                            }
                                            current = current.next;
                                        }
                                        System.out.println("Total order sudah diproses");
                                        System.out.println("Jumlah transaksi: " + count);
                                        System.out.println("Total nilai: Rp " + total);
                                    }
                                    break;
                                    
                                case 2:
                                    // Total belum diproses
                                    {
                                        double total = 0;
                                        int count = 0;
                                        NodeTransaksi current = antrian.getFront();
                                        while (current != null) {
                                            if (current.data.getStatus() == 0) {
                                                total += current.data.getTotalHarga();
                                                count++;
                                            }
                                            current = current.next;
                                        }
                                        System.out.println("Total order belum diproses");
                                        System.out.println("Jumlah transaksi: " + count);
                                        System.out.println("Total nilai: Rp " + total);
                                    }
                                    break;
                                    
                                case 3:
                                    {
                                        System.out.println("\nUbah Harga Barang");
                                        for (int i = 0; i < daftarProduk.length; i++) {
                                            System.out.println((i + 1) + ". " + daftarProduk[i].getNama() + " : Rp " + daftarProduk[i].getHarga());
                                        }
                                        System.out.print("Pilih nomor barang (1-" + daftarProduk.length + "): ");
                                        int pilihBarang = scan.nextInt();
                                        scan.nextLine();
                                        
                                        if (pilihBarang < 1 || pilihBarang > daftarProduk.length) {
                                            System.out.println("❌ Pilihan salah!");
                                        } else {
                                            System.out.print("Masukkan harga baru: ");
                                            double hargaBaru = scan.nextDouble();
                                            scan.nextLine();
                                            daftarProduk[pilihBarang - 1].setHarga(hargaBaru);
                                            System.out.println("✅ Harga " + daftarProduk[pilihBarang - 1].getNama() + 
                                                             " diubah menjadi Rp " + hargaBaru);
                                        }
                                    }
                                    break;
                                    
                                case 4:
                                    {
                                        double[] totalPerJenis = new double[daftarProduk.length];
                                        double totalSemua = 0;
                                        
                                        NodeTransaksi current = antrian.getFront();
                                        while (current != null) {
                                            if (current.data.getStatus() == 1) {
                                                NodeProduk produkNode = current.data.getHeadBelanja();
                                                while (produkNode != null) {
                                                    String namaProduk = produkNode.data.getNama();
                                                    for (int i = 0; i < daftarProduk.length; i++) {
                                                        if (namaProduk.contains(daftarProduk[i].getNama())) {
                                                            totalPerJenis[i] += produkNode.data.getHarga();
                                                            totalSemua += produkNode.data.getHarga();
                                                            break;
                                                        }
                                                    }
                                                    produkNode = produkNode.next;
                                                }
                                            }
                                            current = current.next;
                                        }

                                        System.out.println("\nLaporan Penjualan Per Jenis Barang");
                                        System.out.println("Total pendapatan: Rp " + totalSemua);
                                        for (int i = 0; i < daftarProduk.length; i++) {
                                            System.out.println((i + 1) + ". " + daftarProduk[i].getNama() + " : Rp " + totalPerJenis[i]);
                                        }
                                    }
                                    break;
                                    
                                case 5:
                                    {
                                        System.out.println("\nLaporan Total Belanja Member");
                                        for (int i = 0; i < idMember.length; i++) {
                                            System.out.println((i + 1) + ". " + idMember[i] + " : Rp " + totalBelanjaMember[i]);
                                        }
                                    }
                                    break;
                                    
                                case 6:
                                    {
                                        double[] totalPerJenis = new double[daftarProduk.length];
                                        
                                        NodeTransaksi current = antrian.getFront();
                                        while (current != null) {
                                            if (current.data.getStatus() == 1) {
                                                NodeProduk produkNode = current.data.getHeadBelanja();
                                                while (produkNode != null) {
                                                    String namaProduk = produkNode.data.getNamaProduk();
                                                    for (int i = 0; i < daftarProduk.length; i++) {
                                                        if (namaProduk.contains(daftarProduk[i].getNamaProduk())) {
                                                            totalPerJenis[i] += produkNode.data.getHarga();
                                                            break;
                                                        }
                                                    }
                                                    produkNode = produkNode.next;
                                                }
                                            }
                                            current = current.next;
                                        }

                                        System.out.println("\nGrafik Penjualan (Skala Puluhan Ribu)");
                                        
                                        for (int i = 0; i < daftarProduk.length; i++) {
                                            int skala = (int)(totalPerJenis[i] / 10000);
                                            System.out.print(daftarProduk[i].getNamaProduk() + " : ");
                                            for (int j = 0; j < skala; j++) {
                                                System.out.print("X");
                                            }
                                            System.out.println(" " + (int) totalPerJenis[i]);
                                        }
                                    }
                                    break;
                                    
                                case 7:
                                    System.out.println("Kembali ke menu utama...");
                                    pemilik = false;
                                    break;
                                    
                                default:
                                    System.out.println("❌ Pilihan salah!");
                            }
                        }
                    } else {
                        System.out.println("❌ Password salah!");
                    }
                    break;
                case 5:
                    System.out.println("\n👋 Terima kasih telah menggunakan aplikasi!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("❌ Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scan.close();
    }
}