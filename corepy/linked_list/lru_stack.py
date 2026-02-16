class Lru:
    def __init__(self, capacity):
        self.capacity = capacity
        self.stack = [] # lru stack[0], mru stack[-1]
        self.cache = {}

    def get(self, key):
        if key in self.cache:
            self.stack.remove(key)
            self.stack.append(key)
            return self.cache[key]
        else:
            return None

    def put(self, key, val):
        if key in self.cache:
            self.stack.remove(key)
            self.stack.append(key)
        else:
            if self.capacity == len(self.stack):
                lru = self.stack.pop(0)
                del self.cache[lru]
        self.stack.append(key)
        self.cache[key] = val