import re

inputFile = open("day4/input", "r").read().splitlines()

points = 0

for line in inputFile:
    numbers = line.split("|")
    winningNumbers = set(numbers[0].split(":")[1].split()) # Had trouble with the regex ignoring the number before the colon (IE it would have 18 as a winning number for card 186:)
    selectedNumbers = set(re.findall("\d+", numbers[1]))
    matchedNumbers = winningNumbers.intersection(selectedNumbers)

    matches = len(matchedNumbers)

    if matches > 0:
        points += 2 ** (matches -1)

print(points)