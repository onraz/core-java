from utilties.storagenode import servers_upload, servers_fetch


def hash_fn(key):
    return sum(bytearray(key, 'utf-8')) % 10

def hash_fetch(key):
    return sum(bytearray(key, 'utf-8')) % 9

def upload(file_name):
    index = hash_fn(file_name)
    node = servers_upload[index]
    print(f"Uploading to {node.name}")


def fetch(file_name):
    index = hash_fetch(file_name)
    node = servers_fetch[index]
    print(f"Fetching from {node.name}")


if __name__ == '__main__':
    for i in range(10):
        file_name = f'pic_{i}.txt'
        upload(file_name)
        fetch(file_name)