
def is_power_of_two(num: int) -> bool:
    if num <= 0:
        return False
    else:
        return num & (num-1) == 0



print(is_power_of_two(2))
print(is_power_of_two(3))
print(is_power_of_two(4))
print(is_power_of_two(14))