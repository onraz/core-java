import hashlib
from bisect import bisect_right

class ConsistentHash:
    def __init__(self):
        self._keys = [] # indexes taken on the ring
        self.nodes = [] # nodes in the ring
        self.total_slots = 3000

    def hash_fn(self, key):
        sha = hashlib.sha256()
        sha.update(bytes(key, 'utf-8'))
        return int(sha.hexdigest(), 16) % self.total_slots

    def add_node(self, node):
        if len(self._keys) == self.total_slots:
            raise Exception('Hash Ring is full')

        key = self.hash_fn(node.host)
        index = bisect_right(self._keys, key)

        # as we do bisect_right, check if preceding position already has key
        if index > 0 and self._keys[index - 1] == key:
            raise Exception('Collision Detected')

        self.nodes.insert(index, node)
        self._keys.insert(index, key)
        return key

    def upload(self, file_name):
        key = self.hash_fn(file_name)
        index = bisect_right(self._keys, key)
        host = self.nodes[index].host
        name = self.nodes[index].name
        print(f'Uploading: {file_name} To: {name} Host: {host}')

    def fetch(self, file_name):
        key = self.hash_fn(file_name)
        index = bisect_right(self._keys, key)
        host = self.nodes[index].host
        name = self.nodes[index].name
        print(f'Fetching: {file_name} To: {name} Host: {host}')