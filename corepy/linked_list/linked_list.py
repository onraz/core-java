class Node:
    def __init__(self, value, next=None):
        self.value = value
        self.next = next

class LinkedList:
    def __init__(self):
        self.head = None

    def insert(self, node: Node):
        if self.head is None:
            self.head = node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = node

    def insert_at_head(self, value) -> None:
        self.head = Node(value, self.head)

    def print(self):
        current = self.head
        while current:
            print(current.value)
            current = current.next

    def delete(self, val):
        current = self.head
        previous = None

        while current and current.value != val:
            previous = current
            current = current.next

        if current:
            previous.next = current.next

    def reverse(self):
        if self.head is None or self.head.next is None:
            return
        current = self.head
        previous = None
        while current:
            next_node = current.next
            current.next = previous
            previous = current
            current = next_node
        self.head = previous

    def __iter__(self):
        current = self.head
        while current:
            yield str(current.value)
            current = current.next

    def __repr__(self) -> str:
        return " -> ".join(self) or "Empty"


def merge(l1, l2):
    prehead = Node(-1)
    current = prehead

    while l1 and l2:
        if l1.value > l2.value:
            current.next = l1
            l1 = l1.next
        else:
            current.next = l2
            l2 = l2.next

    current.next = l1 or l2
    return prehead.next

l3 = Node(30)
l2 = Node(20)
l1 = Node(10)

l = LinkedList()
l.insert(l1)
l.insert(l2)
l.insert(l3)
l.reverse()
l.print()
l.insert_at_head(90)
print(l)