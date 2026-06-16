public class Node {
    Transaksi data;
    Node next;

    public Node(Transaksi data) {
        this.data = data;
        this.next = null;
    }
}

class NodeProduk {
    Produk data;
    NodeProduk next;

    public NodeProduk(Produk data) {
        this.data = data;
        this.next = null;
    }
}

class NodeTransaksi {
    Transaksi data;
    NodeTransaksi next;
    
    public NodeTransaksi(Transaksi data) {
        this.data = data;
        this.next = null;
    }
}