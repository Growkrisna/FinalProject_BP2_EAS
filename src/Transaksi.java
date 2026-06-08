public class Transaksi {
    int idTransaksi;
    int idPembeli;
    int status;
    double totalHarga;
    Produk head;
    Produk tail;
    Transaksi next;

    public Transaksi(int idTransaksi, int idPembeli, int status, double totalHarga) {
        this.idTransaksi = idTransaksi;
        this.idPembeli = idPembeli;
        this.status = 0;
        this.totalHarga = 0.0;
        this.head = null;
        this.tail = null;
        this.next = null;
    }

    void addProduk(Produk newProduk) {
        Produk addProduk = new Produk(newProduk.getNamaProduk(), newProduk.getHarga());
        if (head == null) {
            head = addProduk;
            tail = addProduk;
        } else {
            tail.next = addProduk;
            tail = addProduk;
        }
        totalHarga += newProduk.getHarga();
        System.out.println("Produk " + newProduk.getNamaProduk() + " ditambahkan...");
    }

    void lihatBelanja() {
        if (head == null) {
            System.out.println("Keranjang kosong!");
            return;
        }
        System.out.println("|| Daftar Belanja ||");
        Produk current = head;
        while (current != null) {
            System.out.println(current.getInfoProduk());
            current = current.next;
        }
        System.out.println("Total: Rp " + totalHarga);
    }
    
    void tampil() {
        System.out.println("[" + idTransaksi + "] " + idPembeli + " - Rp " + totalHarga + " (Status: " + (status==1?"Sudah":"Belum") + ")");
    }

    void setStatus(int status) {
        this.status = status;
    }

    int getidTransaksi() {
        return idTransaksi;
    }

    int getidPembeli() {
        return idPembeli;
    }

    int getStatus() {
        return status;
    }

    double getTotalHarga() {
        return totalHarga;
    }
}
