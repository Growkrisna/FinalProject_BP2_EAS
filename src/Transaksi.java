public class Transaksi {
    String idTransaksi;
    String namaPembeli;
    int memberIndex;
    int status;
    double totalHarga;
    NodeProduk headBelanja;
    NodeProduk tailBelanja;
    Transaksi next;

    public Transaksi(String idTransaksi, String namaPembeli) {
        this.idTransaksi = idTransaksi;
        this.namaPembeli = namaPembeli;
        this.memberIndex = -1;
        this.status = 0;
        this.totalHarga = 0.0;
        this.headBelanja = null;
        this.tailBelanja = null;
        this.next = null;
    }

    void tambahProduk(Produk newProduk) {
        Produk addProduk = new Produk(newProduk.getNamaProduk(), newProduk.getHarga());
        NodeProduk nodeBaru = new NodeProduk(addProduk);
        if (headBelanja == null) {
            headBelanja = nodeBaru;
            tailBelanja = nodeBaru;
        } else {
            tailBelanja.next = nodeBaru;
            tailBelanja = nodeBaru;
        }
        totalHarga += newProduk.getHarga();
        System.out.println("Produk " + newProduk.getNamaProduk() + " ditambahkan...");
    }

    void lihatKeranjang() {
        if (headBelanja == null) {
            System.out.println("Keranjang kosong!");
            return;
        }
        System.out.println("|| Daftar Belanja ||");
        NodeProduk current = headBelanja;
        while (current != null) {
            System.out.println(current.data.getInfoProduk());
            current = current.next;
        }
        System.out.println("Total: Rp " + totalHarga);
    }

    void hapusSemua() {
        headBelanja = null;
        tailBelanja = null;
        totalHarga = 0.0;
        System.out.println("Keranjang berhasil dikosongkan.");
    }

    void applyDiskon(int persen) {
        if (persen <= 0) {
            return;
        }
        totalHarga = totalHarga - (totalHarga * persen / 100.0);
        System.out.println("Diskon " + persen + "% berhasil diterapkan.");
    }
    
    void tampil() {
        System.out.println("[" + idTransaksi + "] " + namaPembeli + " - Rp " + totalHarga + " (Status: " + (status==1?"Sudah":"Belum") + ")");
    }

    void setStatus(int status) {
        this.status = status;
    }

    void setMemberIndex(int memberIndex) {
        this.memberIndex = memberIndex;
    }

    String getidTransaksi() {
        return idTransaksi;
    }

    String getNamaPembeli() {
        return namaPembeli;
    }

    int getStatus() {
        return status;
    }

    double getTotalHarga() {
        return totalHarga;
    }

    int getMemberIndex() {
        return memberIndex;
    }

    NodeProduk getHeadBelanja() {
        return headBelanja;
    }


}
