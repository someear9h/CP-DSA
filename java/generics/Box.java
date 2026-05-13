package generics;

public class Box <T> {
    T item;

    // setter
    public void setItem(T item) {
        this.item = item;
    }

    // getter
    public T getItem() {
        return this.item;
    }
}
