import java.util.ArrayList;

/**
 * Represents a generic queue data structure.
 * 
 * @param <T> The type of elements this queue holds.
 */
public class MyQueue<T> {

    // ArrayList to hold queue elements
    private ArrayList<T> data;
    
    // Maximum number of elements the queue can hold
    private int maxSize;

    /**
     * Constructor that initializes the queue with a given maximum size.
     * 
     * @param maxSize The maximum number of elements the queue can hold.
     */
    public MyQueue(int maxSize) {
        this.data = new ArrayList<>();
        this.maxSize = maxSize;
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Adds an item to the end of the queue.
     * 
     * @param item The item to be added to the end of the queue.
     * @return true if the item was added successfully.
     * @throws QueueOverflowException If the queue is already full.
     */
    public boolean enqueue(T item) throws QueueOverflowException {
        if (data.size() < maxSize) {
            data.add(item);
            return true;
        } else {
            throw new QueueOverflowException("Queue is full");
        }
    }

    /**
     * Removes and returns the item at the front of the queue.
     * 
     * @return The item at the front of the queue.
     * @throws QueueUnderflowException If the queue is empty.
     */
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException("Queue is empty");
        }
        return data.remove(0);
    }

    /**
     * Returns the number of items in the queue.
     * 
     * @return The number of items in the queue.
     */
    public int size() {
        return data.size();
    }

    /**
     * Checks if the queue is full.
     * 
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull() {
        return data.size() == maxSize;
    }

    /**
     * Returns a string representation of the queue without any delimiters.
     * 
     * @return A string representation of the queue.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : data) {
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the queue using the specified delimiter.
     * 
     * @param delimiter The delimiter to be used between elements.
     * @return A string representation of the queue.
     */
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i));
            if (i < data.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Fills the queue with items from the provided ArrayList.
     * 
     * @param items The list of items to be added to the queue.
     * @throws QueueOverflowException If there isn't enough space in the queue for all items.
     */
    public void fill(ArrayList<T> items) throws QueueOverflowException {
        for (T item : items) {
            enqueue(item);
        }
    }
}

