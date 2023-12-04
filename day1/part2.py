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

# Plan of attack:
# Now we need to check what digits we find and at what position
# Then I want to check whether the line contains any of the strings
# I want to know at what poistion the string is found and crosscheck whether it would be either first or last
# Then get the corresponding integer value, append it and add it to the total

for line in inputFile:
    firstDigit = None
    lastDigit = None

    for i in range(0, len(line)): # Iterate throug all characters
        if line[i].isnumeric(): # If a character is a number
            if not firstDigit: # If the first digit is not set, it is the first digit we encounter
                firstDigit = (i, line[i])
                lastDigit = (i, line[i]) # And by default also the last character
            else: # If first digit is already set, we only change the last digit
                lastDigit = (i, line[i])
    

    # Now we need to check the digit words in the line

    index = -1 # index where digit can be found, -1 for default negative result
    foundDigit = None # the foundDigit
    for digit in DIGITS: # Loop through digits and check if line contains said digit
        index = line.find(digit)
        if (index != -1): 
            foundDigit = digit # if index != -1, than current digit is found in line
            if firstDigit:
                if index < firstDigit[0]: # if index is smaller than FirstDigit's index, change it
                    firstDigit = (index, str(DIGITS.index(digit)+1)) # The value of the digit is the position in the array + 1
                    if not lastDigit: # also set lastDigit if it wasn't set yet
                        lastDigit = firstDigit
                if index > lastDigit[0]: # if index is bigger than lastDigit's index, change it
                    lastDigit = (index, str(DIGITS.index(digit)+1))
            else: # if firstDigit is not set, no numbers where found so far, so we can set both first and last digit
                firstDigit = (index, str(DIGITS.index(digit)+1))
                lastDigit = (index, str(DIGITS.index(digit)+1))
            lastOccurenceIndex = line.rfind(digit) # Finde the last index of the digit to check if the word is there more than once
            if index is not lastOccurenceIndex: # When the indexes are different, there is another instance of the word
                if lastOccurenceIndex > lastDigit[0]: # if found index is bigger than lastDigit's index, change it
                    lastDigit = (lastOccurenceIndex, str(DIGITS.index(digit)+1))

    result = int(firstDigit[1] + lastDigit[1])
    total += result

print(f"Total is: {total}")