package dev;

/**
 * Represents a consistent hash cluster where items (both objects,
 * servers and their replicas) are positioned on an abstract circle.
 * <p>
 * To locate an item, the server/replica at the item's hash or its nearest
 * clockwise server/replica is chosen.
 * <p>
 * The hash function to use with this scheme should create uniform, random
 * distribution of items.
 *
 * @param <T> the type of server/replica node for consistent hash cluster
 */
public interface ConsistentHashCluster<T> {
    void addToCircle(T node);

    void removeFromCircle(T node);

    T getServer(Object key);
}
