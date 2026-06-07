public class produk {
    private String namaProduk;
    private double harga;
    
    public produk(String namaProduk, double harga) {
        this.namaProduk = namaProduk;
        this.harga = harga;
    }

    String getNamaProduk() {
        return namaProduk;
    }

    double getHarga() {
        return harga;
    }
}
