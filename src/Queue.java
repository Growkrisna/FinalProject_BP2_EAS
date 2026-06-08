public class Queue {
    Node front;
    Node rear;

    Queue() {
        this.front = null;
        this.rear = null;
    }

    void enqueue(Transaksi data) {  // UBAH: parameter jadi Transaksi
        Node newNode = new Node(data);
        if (this.rear == null) {
            this.front = newNode;
            this.rear = newNode;
        } else {
            this.rear.next = newNode;
            this.rear = newNode;
        }
        System.out.println("Transaksi " + data.getidTransaksi() + " masuk antrian.");
    }

    void dequeue() {
        if (this.front == null) {
            System.out.println("Antrian kosong..");
        } else {
            this.front.data.setStatus(1);
            System.out.println("Transaksi " + this.front.data.getidTransaksi() + " telah diproses.");
            
            this.front = this.front.next;
            if (this.front == null) {
                this.rear = null;
            }
        }
    }

    void showUnprocessed() {
        if (this.front == null) {
            System.out.println("Antrian kosong..");
            return;
        }
        Node current = this.front;
        int no = 1;
        System.out.println("|| TRANSAKSI BELUM DIPROSES ||");
        while (current != null) {
            if (current.data.getStatus() == 0) {
                System.out.print(no++ + ". ");
                current.data.tampil();
            }
            current = current.next;
        }
    }

    void show() {
        if (this.front == null) {
            System.out.println("Antrian kosong..");
            return;
        }
        Node current = this.front;
        System.out.println("|| SEMUA TRANSAKSI ||");
        while (current != null) {
            current.data.tampil();
            current = current.next;
        }
    }
}