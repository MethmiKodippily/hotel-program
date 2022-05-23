//Any code taken from other sources is referenced within my code solution.

//https://www.programiz.com/dsa/circular-queue
public class CircularQueue {
    int SIZE = 8; // Length of Circular Queue
    int front, rear;

    CircularQueue() {
        front = -1;
        rear = -1;
    }

    // Check if the queue is full
    boolean isFull() {
        if (front == 0 && rear == SIZE - 1) {
            return true;
        }
        if (front == rear + 1) {
            return true;
        }
        return false;
    }

    // Check if the queue is empty
    boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    // Adding an element
    int enQueue() {
        if (front == -1)
            front = 0;
        rear = (rear + 1) % SIZE;
        return rear;
    }

    // Removing an element
    int deQueue() {
        int element;

        element = front;
        if (front == rear) {
            front = -1;
            rear = -1;
        } //Q has only one element, so we reset the queue after deleting it.
        else {
            front = (front + 1) % SIZE;
        }
        return (element);

    }

}
