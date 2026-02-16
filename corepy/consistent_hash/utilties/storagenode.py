
class StorageNode:
    def __init__(self, name=None, host=None):
        self.name = name
        self.host = host

servers_upload = [
    StorageNode(name='A', host='aserver1.local.3000'),
    StorageNode(name='B', host='bserver2.local.3000'),
    StorageNode(name='C', host='cserver3.local.3000'),
    StorageNode(name='D', host='dserver4.local.3000'),
    StorageNode(name='E', host='eserver5.local.3000'),
    StorageNode(name='F', host='fserver6.local.3000'),
    StorageNode(name='G', host='gserver7.local.3000'),
    StorageNode(name='H', host='hserver8.local.3000'),
    StorageNode(name='I', host='iserver8.local.3000'),
    StorageNode(name='J', host='jserver10.local.3000')
]

servers_fetch = [
    StorageNode(name='B', host='bserver2.local.3000'),
    StorageNode(name='C', host='cserver3.local.3000'),
    StorageNode(name='D', host='dserver4.local.3000'),
    StorageNode(name='E', host='eserver5.local.3000'),
    StorageNode(name='F', host='fserver6.local.3000'),
    StorageNode(name='G', host='gserver7.local.3000'),
    StorageNode(name='H', host='hserver8.local.3000'),
    StorageNode(name='I', host='iserver8.local.3000'),
    StorageNode(name='J', host='jserver10.local.3000')
]