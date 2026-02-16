class Dir:
    def __init__(self, id, parent=None):
        self.id = id
        self.parent = parent
        self.children = []

    def print(self, indent=0):
        margin = " " * indent
        print(f"{margin}-{self.id}-{has_access(self)}")
        for child in self.children:
            child.print(indent+2)


root = Dir(0)
grant = set()
deny = set()

def grantd(dir_id: int):
    if dir_id in deny:
        deny.remove(dir_id)
    grant.add(dir_id)

def denyd(dir_id: int):
    if dir_id in grant:
        grant.remove(dir_id)
    deny.add(dir_id)

def has_access(dir: Dir):
    if dir.id in grant:
        return True

    if dir.id in deny:
        return False

    if dir.parent:
        return has_access(dir.parent)

    return True

dir1 = Dir(1, root)
dir1.children.extend([Dir(12, dir1), Dir(13, dir1)])
root.children.append(dir1)

dir2 = Dir(2, root)
dir2.children.extend([Dir(22, dir2), Dir(23, dir2)])
root.children.append(dir2)


dir3 = Dir(3, root)
root.children.append(dir3)

denyd(2)
grantd(23)
denyd(1)

root.print()