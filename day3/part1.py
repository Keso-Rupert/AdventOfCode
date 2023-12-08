inputFile = open("day3/input.txt")

# schematic = len(inputFile.readlines())
schematic = []
i = 0

partsSymbols = ["*", "#", "+", "$", "=", "=", "/", "@", "%", "&", "-"]
partNumbersSum = 0
foundNumbers = []  # Array of found numbers, consisting of the number and the index (123, (1,2))
foundParts = []  # Same for the parts

# First build the matrix
for line in inputFile:
    schematic.insert(i, [])
    j = 0
    for char in line.strip("\n"):
        schematic[i].insert(j, char)
        j += 1
    i += 1


for i in range(0, len(schematic)):
    currentDigit = ""

    for j in range(0, len(schematic[i])):
        currentChar = schematic[i][j]        

        if not currentChar.isalnum():
            if currentDigit:  # This means we reached the end of a digit and need to store it and its location
                foundNumbers.append((currentDigit, (i, j-len(currentDigit))))
                currentDigit = ""

            if not currentChar == ".":
                foundParts.append((currentChar, (i, j))) # tuple of part and index, like ("*"", (1,2))
        elif currentChar.isdigit():
            currentDigit += currentChar  # Add found digit to the currentDigit
    if currentChar:  # Quite literal edge case where the number is on the edge of the matrix
        foundNumbers.append((currentDigit, (i, j-len(currentDigit))))


"""
Ok what do we need to check?
I need to check every field around the number.
If the number is 123, on coordinates 8,9, then on the x axis, I need to check 7, 8, 9. (-1, 0, +1)
On the y axis, I need to check 8, but then consider the length of the string, so that would be 12 (so -1, +3)
THEN I need to consider the bounds of the matrix, which is len(schematic[i]) and len(schematic[i][j]) if I'm not mistaken.
x cannot be higher then the first, y cannot be higher then the second
"""
def checkForAdjecentParts(number):
    x, y = number[1][0], number[1][1]

    for i in range(x-1, x+2):  # Do I need to +2 because range is not inclusive?
        for j in range(y-1, (y+len(number[0])+1)):  # Took me way too long to figure out this should only be a +1
            if i < 0 or j < 0 or i >= len(schematic) or j >= len(schematic):  # Consider the bounds of the matrix
                continue
            if schematic[i][j] in partsSymbols:
                return True

    return False


for number in foundNumbers:
    if checkForAdjecentParts(number):
        partNumbersSum += int(number[0])

print(partNumbersSum)