import java.util.ArrayList;

/**
 * Represents a generic stack data structure.
 * 
 * @param <T> The type of elements this stack holds.
 */
public class MyStack<T> {
    // Array to hold stack elements
    private Object[] elements;
    
    // Index representing the top of the stack. -1 indicates an empty stack.
    private int top;
    
    // Maximum number of elements the stack can hold.
    private int capacity;

    /**
     * Constructor that initializes the stack with a given capacity.
     * 
     * @param capacity The maximum number of elements the stack can hold.
     */
    public MyStack(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        top = -1;
    }

    /**
     * Pushes an item onto the top of this stack.
     * 
     * @param item The item to be pushed onto this stack.
     * @return true if the push was successful.
     * @throws StackOverflowException If the stack is already full.
     */
    public boolean push(T item) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException("Stack is full");
        }
        elements[++top] = item;
        return true;
    }

    /**
     * Removes and returns the top item from this stack.
     * 
     * @return The item at the top of this stack.
     * @throws StackUnderflowException If the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        return (T) elements[top--];
    }

    /**
     * Returns (without removing) the top item from this stack.
     * 
     * @return The item at the top of this stack.
     * @throws StackUnderflowException If the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        return (T) elements[top];
    }

    /**
     * Checks if the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Checks if the stack is full.
     * 
     * @return true if the stack is full, false otherwise.
     */
    public boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * Returns the number of items in the stack.
     * 
     * @return The number of items in the stack.
     */
    public int size() {
        return top + 1;
    }
    
    /**
     * Builds a string representation of the stack using the specified delimiter.
     * 
     * @param delimiter The delimiter to be used between elements.
     * @return A string representation of the stack.
     */
    private String stringRepresentation(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(elements[i]);
            if(i < top) { 
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the stack without any delimiters.
     * 
     * @return A string representation of the stack.
     */
    @Override
    public String toString() {
        return stringRepresentation(""); 
    }

    /**
     * Returns a string representation of the stack using the specified delimiter.
     * 
     * @param delimiter The delimiter to be used between elements.
     * @return A string representation of the stack.
     */
    public String toString(String delimiter) {
        return stringRepresentation(delimiter);
    }

    /**
     * Fills the stack with items from the provided ArrayList.
     * 
     * @param list The list of items to be pushed onto the stack.
     * @throws StackOverflowException If there isn't enough space in the stack for all items.
     */
    public void fill(ArrayList<T> list) throws StackOverflowException {
        for (T item : list) {
            push(item);
        }
    }
}
