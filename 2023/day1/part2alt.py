import re

inputFile = open("day1/input.txt", "r")
total = 0
ONE = "one"
TWO = "two"
THREE = "three"
FOUR = "four"
FIVE = "five"
SIX = "six"
SEVEN = "seven"
EIGHT = "eight"
NINE = "nine"
DIGITS = [ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE]

for line in inputFile:
    firstDigit = None
    lastDigit = None

    for i in range(0, len(line)): # Iterate throug all characters
        if line[i].isnumeric(): # If a character is a number
            if not firstDigit: # If the first digit is not set, it is the first digit we encounter
                firstDigit = line[i]
                lastDigit = line[i] # And by default also the last character
            else: # If first digit is already set, we only change the last digit
                lastDigit = line[i]
        for digit in DIGITS:
            if line[i:].startswith(digit):
                if not firstDigit:
                    firstDigit = str(DIGITS.index(digit)+1)
                    lastDigit = str(DIGITS.index(digit)+1)
                else:
                    lastDigit = lastDigit = str(DIGITS.index(digit)+1)

    result = int(firstDigit + lastDigit)
    total += result

print(f"Total is: {total}")