package dev;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;

/**
 *  A {@code ConsistentHash} cluster backed by a sorted map.
 *
 * @param <T> the type of server/replica nodes in the cluster
 */
public class SortedMapConsistentHashCluster<T> implements ConsistentHashCluster<T> {
    private final Function<Object, Integer> hashFunction;
    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle;

    public SortedMapConsistentHashCluster(Function<Object, Integer> hashFunction,
                                          int numberOfReplicas,
                                          Collection<T> servers) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        this.circle = new TreeMap<>();

        for (T node : servers) {
            addToCircle(node);
        }
    }

    /**
     * Adds a given server node to the virtual circle, along with replicas
     *
     * @param node the server node to add to circle
     */
    @Override
    public void addToCircle(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.apply(generateReplicaName(node, i)), node);
        }
    }

    @Override
    public void removeFromCircle(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.apply(generateReplicaName(node, i)));
        }
    }

    /**
     * Return the server or replica associated with the given key.
     *
     * @param key the key to lookup in server cluster
     * @return the server cluster at key hash location, or the nearest server on the right (clockwise)
     */
    @Override
    public T getServer(Object key) {
        final Integer keyHash = hashFunction.apply(key);
        if (circle.containsKey(keyHash)) {
            // already a server node/replica at key hash, just return it
            return circle.get(keyHash);
        } else {
            // tail map returns the right sub map (bisect) including the keyHash if it exists in map
            final SortedMap<Integer, T> tailMap = circle.tailMap(keyHash);
            final Integer rightMostServerHash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
            return circle.get(rightMostServerHash);
        }
    }

    private String generateReplicaName(T node, int i) {
        return node.toString() + "_" + i;
    }
}
