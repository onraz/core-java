list1 = [9, 8, 2, 4, 11, 5]
list = [ x**2 for x in list1 if x % 2 == 0]
max = list[0]
min = list[0]
for idx, num in enumerate(list):
    if max < list[idx]:
        max = list[idx]
    if min > list[idx]:
        min = list[idx]

for i in range(1, len(list)):
    temp = list[i]
    list[i] = list[i-1]
    list[i-1] = temp

print(f"Max is {max} , min {min} of list {list}")

dict = { 'name': 'raz', 'age': 12, 'zip': 95014}

for k, v in dict.items():
    if v == 12:
        print("found 12")
    elif v == 95014:
        print("zipcode")
    else:
        print(f"{k} -> {v}")