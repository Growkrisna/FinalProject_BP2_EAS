public class Queue {
    NodeTransaksi front;
    NodeTransaksi rear;

    Queue() {
        this.front = null;
        this.rear = null;
    }

    void enqueue(Transaksi data) {
        NodeTransaksi newNode = new NodeTransaksi(data);
        if (this.rear == null) {
            this.front = newNode;
            this.rear = newNode;
        } else {
            this.rear.next = newNode;
            this.rear = newNode;
        }
        System.out.println("Transaksi " + data.getidTransaksi() + " masuk antrian.");
    }

    Transaksi dequeue() {
        NodeTransaksi current = this.front;
        while (current != null && current.data.getStatus() == 1) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Antrian kosong..");
            return null;
        } else {
            current.data.setStatus(1);
            System.out.println("Transaksi " + current.data.getidTransaksi() + " telah diproses.");
            return current.data;
        }
    }

    void tampilkanBelumDiproses() {
        if (this.front == null) {
            System.out.println("Antrian kosong..");
            return;
        }
        NodeTransaksi current = this.front;
        int no = 1;
        System.out.println("|| TRANSAKSI BELUM DIPROSES ||");
        boolean ada = false;
        while (current != null) {
            if (current.data.getStatus() == 0) {
                ada = true;
                System.out.print(no++ + ". ");
                current.data.tampil();
            }
            current = current.next;
        }
        if (!ada) {
            System.out.println("Tidak ada transaksi yang belum diproses.");
        }
    }

    boolean hasUnprocessed() {
        NodeTransaksi current = this.front;
        while (current != null) {
            if (current.data.getStatus() == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    NodeTransaksi getFront() {
        return this.front;
    }

    void showUnprocessed() {
        tampilkanBelumDiproses();
    }

    void show() {
        if (this.front == null) {
            System.out.println("Antrian kosong..");
            return;
        }
        NodeTransaksi current = this.front;
        System.out.println("|| SEMUA TRANSAKSI ||");
        while (current != null) {
            current.data.tampil();
            current = current.next;
        }
    }
}