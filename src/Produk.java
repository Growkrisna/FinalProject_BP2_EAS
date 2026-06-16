public class Produk {
    private String namaProduk;
    private double harga;
    Produk next;
    
    public Produk(String namaProduk, double harga) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.next = null;
    }

    String getNama() {
        return namaProduk;
    }

    String getNamaProduk() {
        return namaProduk;
    }

    double getHarga() {
        return harga;
    }

    void setHarga(double harga) {
        this.harga = harga;
    }

    String getInfoProduk() {
        return "Nama Produk: " + namaProduk + ", " + harga;
    }
}
