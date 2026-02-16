package dev;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Demo {
    record Server(String name) {
    }

    public static void main(String[] args) {
        final Server[] servers = {
                new Server("A"),
                new Server("B"),
                new Server("C"),
                new Server("D")
        };
        final ConsistentHashCluster<Server> cluster = new SortedMapConsistentHashCluster<>(
                key -> {
                    try {
                        return new BigInteger(MessageDigest.getInstance("MD5").digest(key.toString().getBytes())).intValue();
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                },
                3,
                List.of(servers)
        );

        System.out.println("Locating A : " + cluster.getServer("A"));
        System.out.println("Locating B : " + cluster.getServer("B"));
        System.out.println("Locating C : " + cluster.getServer("C"));
        System.out.println("Locating D : " + cluster.getServer("D"));
        System.out.println("Locating Aa : " + cluster.getServer("Aa"));
        System.out.println("Locating Bb : " + cluster.getServer("Bb"));
        System.out.println("Locating Cc : " + cluster.getServer("Cc"));
        System.out.println("Locating Dd : " + cluster.getServer("Dd"));
        System.out.println("Locating Aa1 : " + cluster.getServer("Aa1"));
        System.out.println("Locating Bb1 : " + cluster.getServer("Bb1"));
        System.out.println("Locating Cc1 : " + cluster.getServer("Cc1"));
        System.out.println("Locating Dd1 : " + cluster.getServer("Dd1"));

    }

}
