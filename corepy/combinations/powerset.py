def power_set_iterative(input_list):
    power_set = [[]] # Initialize with the empty set
    for item in input_list:
        for subset in power_set:
            # Add item to each existing subset
            power_set = power_set + [subset + [item]] # add a new pair to power_set by adding an item to existing subset
    return power_set


def power_set(input_list):
    if not input_list:
        return [[]]

    input_list.sort(key=lambda i: i.start)
    first_element = [input_list[0]]
    rest_of_subsets = power_set(input_list[1:])
    """
        It then combines the power set of the rest with the power set of the rest, with the first element added to each subset.
        list comprehension
        [[4] + s for s in [[1,2],[2,3]]]
        = [[4, 1, 2], [4, 2, 3]]
    """
    return rest_of_subsets + [first_element + subset for subset in rest_of_subsets]

print(power_set([1]))