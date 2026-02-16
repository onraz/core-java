class Node:
    def __init__(self, val):
        self.val = val
        self.prev = None
        self.next = None


class LinkedList:
    def __init__(self):
        self.head = Node(0)
        self.tail = Node(0)
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, node):
        prev = node.prev
        next = node.next
        prev.next = next
        next.prev = prev

    def print(self):
        curr = self.head.next
        while curr != self.tail:
            print(f"{curr.val}", end="-->")
            curr = curr.next
        print("")

    def add_mru(self, val):
        mru = Node(val)
        prev = self.tail.prev
        mru.next = self.tail
        mru.prev = prev
        prev.next = mru
        self.tail.prev = mru

    def add_lru(self, val):
        lru = Node(val)
        next = self.head.next
        lru.prev = self.head
        lru.next = next
        next.prev = lru
        self.head.next = lru

    def remove_val(self, val):
        node = self.find(val)
        self.remove(node)

    def find(self, val):
        current = self.head.next
        while current != self.tail:
            if current.val == val:
                return current
            current = current.next
        return None

class Lru:

    def __init__(self, capacity):
        self.list = LinkedList()
        self.cache = {}
        self.capacity = capacity

    def get(self, key):
        if key in self.cache:
            node = self.cache[key]
            self.list.remove(node)
            self.list.add_mru(node)
            return node.val
        return None

    def put(self, key, val):
        if key in self.cache:
            node = self.cache[key]
            node.val = val
            self.list.remove(node)
            self.list.add_mru(node)
        else:
            node = Node(val)
            self.cache[key] = node
            self.list.add_mru(node)

            if len(self.cache) > self.capacity:
                self.list.remove(self.list.head.next)
                del self.cache[key]


listy = LinkedList()
listy.add_lru(10)
listy.add_lru(20)
listy.add_mru(30)
listy.add_lru(9)
listy.print()
listy.remove_val(30)
listy.print()
