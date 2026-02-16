sound: str = "nnneeeiiiggghhhnneeiigghhneigh"
animal: str = "neigh"

def min_animals(sound: str) -> int:
    current_animal = 0
    max_animal = 0
    counter = {x: 0 for x in 'neigh'}
    for ch in sound:
        if ch not in 'neigh':
            return -1
        counter[ch] += 1
        if ch == 'n':
            current_animal += 1
            max_animal = max(max_animal, current_animal)
        else:
            prev = 'neigh'['neigh'.index(ch) - 1]
            if counter[prev] < counter[ch]:
                return -1
            if ch == 'h':
                current_animal -= 1
    return max_animal


def min_animal(sound: str) -> int:
    counter = [0] * len("neigh")
    animal_counter = 0
    max_animal = 0

    for ch in sound:
        if ch in 'neigh':
            if ch == 'n':
                animal_counter += 1
                max_animal = max(max_animal, animal_counter)
                counter[0] += 1
            else:
                ch_idx = 'neigh'.index(ch)
                ch_prev_idx = ch_idx - 1
                if counter[ch_idx] > counter[ch_prev_idx]:
                    return -1
                counter[ch_idx] += 1
                if ch == 'h':
                    animal_counter -= 1
        else:
            return -1

    return max_animal


print(min_animal(sound))