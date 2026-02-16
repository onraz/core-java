package functional.examples;

import java.text.Collator;
import java.util.*;

public class ExampleSort {
    static class Group implements Comparable<Group> {
        private final String groupName;
        private final boolean isAdmin;
        Group(String groupName, boolean isAdmin) {
            this.groupName = groupName;
            this.isAdmin = isAdmin;
        }

        @Override
        public int compareTo(Group group) {
            if (this.isAdmin) {
                return -1;
            } else if (group.isAdmin) {
                return 1;
            } else {
                return 0;
            }
        }

        public String getGroupName() {
            return groupName;
        }

        @Override
        public String toString() {
            return "{" + groupName + ": admin=" + isAdmin + '}';
        }
    }

    public static void main(String[] args) {
        final List<Group> groups = new ArrayList<>(List.of(
            new Group("mallory", false),
            new Group("tara", true),
            new Group("alice", true),
            new Group("nika", false),
            new Group("bob", false),
            new Group("gamma", true)
        ));

        System.out.println("Default/insertion order: " + groups);
        Collections.sort(groups);
        System.out.println("Default sorted/lexicographical order: " + groups);
        // So far so good, but what if someone put this in Sorted Set! Surprise!!
        System.out.println("Sorted Set order: " + new TreeSet<>(groups));

        final Collator nameCollator = Collator.getInstance();
        final Comparator<Group> adminComparator = (group1, group2) -> {
            final int lexOrder = nameCollator.compare(group1.groupName, group2.groupName);
            if (group1.isAdmin && group2.isAdmin) {
                return lexOrder; // both are admins, use lex order
            } else if (group1.isAdmin) {
                return -1;
            } else if (group2.isAdmin) {
                return 1;
            } else {
                return lexOrder; // both are admins, use lex order
            }
        };
        groups.sort(adminComparator);
        System.out.println("XSorted using Admin/lexicographical order: " + groups);
    }
}
