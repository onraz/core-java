# Creating dictionaries
# Method 1: Using curly braces {}
person = {"name": "Alice", "age": 30, "city": "New York"}

# Accessing values
name = person["name"]  # Accessing by key
age = person.get("age")  # Using the get() method

# Modifying dictionaries
person["age"] = 31  # Updating an existing key
person["occupation"] = "Engineer"  # Adding a new key-value pair
del person["city"]  # Deleting a key-value pair
occupation = person.pop("occupation") # Removing and returning a key-value pair
person.clear() # Removing all key-value pairs

# Iterating through dictionaries
person = {"name": "Alice", "age": 30, "city": "New York"}
for key in person:  # Iterating through keys
    print(key, person[key])

for key, value in person.items():  # Iterating through key-value pairs
    print(key, value)

# Dictionary methods
keys = person.keys()  # Returns a view object of keys
values = person.values()  # Returns a view object of values
items = person.items()  # Returns a view object of key-value pairs

# Dictionary comprehension
squares = {x: x**2 for x in range(1, 6)} # Creates a dictionary of squares from 1 to 5
print(squares)

portfolio = [
    {'name': 'GOOG', 'shares': 50},
    {'name': 'YHOO', 'shares': 75},
    {'name': 'AOL', 'shares': 20},
    {'name': 'TOL', 'shares': 12},
]

data = [s['shares'] for s in portfolio]
print(data)

min_shares = min(data)
# Or compact list comprehension genaratpr
min_shares = min(s['shares'] for s in portfolio)
min_shares2 = min(portfolio, key=lambda s: s['shares'])
print(min_shares)
print(f"Min: {min_shares2}")

#
# Shows how built in functions can take iterables as well as list comprehension generators
# This saves time to create temporary list
#
def create_csv_row(tuple):
    return ','.join(str(t) for t in tuple)


my_tuple = ('ACME', 12, 5)
print(create_csv_row(my_tuple))