public class Queue {
    Node front;
    Node rear;

    Queue() {
        this.front = null;
        this.rear = null;
    }

    void enqueue(produk data) {
        Node newNode = new Node(data);
        if (this.rear == null) {
            this.front = newNode;
            this.rear = newNode;
        } else {
            this.rear.next = newNode;
            this.rear = newNode;
        }
    }

    void dequeue() {
        if (this.front == null) {
            System.out.println("Antrian kosong..");
        } else {
            this.front = this.front.next;
            System.out.println("Pesanan telah diproses.");
            if (this.front == null) {
                this.rear = null;
            }
        }
    }

    void show(){
        if (this.front == null) {
            System.out.println("Antrian kosong..");
        } else {
            Node current = this.front;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }
    }

    void process(){
        if(front == null){
            System.out.println("Antrian kosong..");
        }else{
            Node current = this.front;
            while(current != null){
                System.out.println("Memproses pesanan: " + current.data);
                current = current.next;
            }
        }
    }
}