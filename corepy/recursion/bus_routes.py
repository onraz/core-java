import collections


class Solution:
    def numBusesToDestination(self, routes: list[list[int]], source: int, target: int) -> int:
        if source == target:
            return 0

        # Map each stop to the routes that include it
        stop_to_routes = collections.defaultdict(list)
        for i, route in enumerate(routes):
            for stop in route:
                stop_to_routes[stop].append(i)

        # BFS initialization
        queue = collections.deque([source])
        buses_taken = 0
        visited_buses = set()  # To keep track of buses already boarded

        while queue:
            buses_taken += 1
            # Process all stops at the current level
            for _ in range(len(queue)):
                current_stop = queue.popleft()

                # Explore all routes available from the current stop
                for bus_index in stop_to_routes[current_stop]:
                    if bus_index in visited_buses:
                        continue

                    visited_buses.add(bus_index)  # Mark bus as visited

                    # Explore all stops on this bus route
                    for next_stop in routes[bus_index]:
                        if next_stop == target:
                            return buses_taken
                        queue.append(next_stop)

        return -1  # Target is unreachable


from collections import defaultdict, deque
from typing import List

class Solution:
    def numBusesToDestination(self, routes: List[List[int]], source: int, target: int) -> int:
        # 🎯 Base case: already at the destination
        if source == target:
            return 0

        # 🗺️ Build mapping: stop -> set of buses that go through it
        stop_to_buses = defaultdict(set)
        for bus_id, stops in enumerate(routes):
            for stop in stops:
                stop_to_buses[stop].add(bus_id)

        # 🧭 Initialize BFS
        queue = deque([(source, 0)])   # (current_stop, buses_taken_so_far)
        visited_stops = set([source])
        visited_buses = set()

        # 🚍 BFS traversal
        while queue:
            stop, buses_taken = queue.popleft()

            # Check if we've reached destination
            if stop == target:
                return buses_taken

            # For every bus passing through this stop
            for bus_id in stop_to_buses[stop]:
                if bus_id in visited_buses:
                    continue
                visited_buses.add(bus_id)

                # For every stop that this bus can reach
                for next_stop in routes[bus_id]:
                    if next_stop not in visited_stops:
                        visited_stops.add(next_stop)
                        queue.append((next_stop, buses_taken + 1))

        # 🚫 Destination not reachable
        return -1
