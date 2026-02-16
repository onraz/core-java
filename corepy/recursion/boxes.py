def boxes(boxes: list[int], warehouse: list[int]):
    place = 0
    boxes.sort(reverse=True)
    for box in boxes:
        if place < len(warehouse) and box <= warehouse[place]:
            place += 1

    return place


def maxBoxesInWarehouse(boxes, warehouse):
    # Step 1: sort boxes ascending
    boxes.sort()

    # Step 2: compute prefix minima for warehouse (tightest height so far)
    for i in range(1, len(warehouse)):
        warehouse[i] = min(warehouse[i], warehouse[i - 1])

    # Step 3: greedy placement from rightmost slot
    i = len(boxes) - 1  # largest box
    j = len(warehouse) - 1  # rightmost warehouse room
    count = 0

    while i >= 0 and j >= 0:
        if boxes[i] <= warehouse[j]:
            count += 1  # box fits
            i -= 1  # next largest box
            j -= 1  # move left in warehouse
        else:
            j -= 1  # try smaller warehouse slot
    return count

print(boxes([4,3,4,1], [5,3,3,4,1]))