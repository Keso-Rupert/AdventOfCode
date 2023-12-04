

inputFile = open("day1/input.txt", "r")
total = 0


for line in inputFile:
    firstDigit = None
    lastDigit = None

    for char in line: # Iterate throug all characters
        if char.isnumeric(): # If a character is a number
            if not firstDigit: # If the first digit is not set, it is the first digit we encounter
                firstDigit = char
                lastDigit = char # And by default also the last character
            else: # If first digit is already set, we only change the last digit
                lastDigit = char
    result = int(firstDigit + lastDigit)
    total += result

print(f"Total is: {total}")