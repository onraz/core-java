# End of statements
msg1 = """test is 
good"""
msg2 = "raz"
msg3 = 'raz2'

if 0 == msg1:
    print("value is 0")
else:
    print("value not zero")

# Java null is not false, but in Python it is
# Empty or None is False in python
abc = None
xyz = False
pqr = 0
cde = []
str = ""
lens = ()

print(msg1)


# Tuples are static and immutable, fixed in size


# Maps
dict = { 'name': 'raz', 'age': 12, 'zip': 95014}

for k,v in dict.items():
    print(k, ' == ', v)

for i in range(0, 5):
    if i == 0:
        print('it starts at 0')
    elif i > 1 and i % 2 == 0:
        print('i is divisible by 2')
    else:
        print(f'all other cases {i}')