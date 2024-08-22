inputFile = open("day3/input.txt")

# schematic = len(inputFile.readlines())
schematic = []
i = 0

partsSymbols = ["*", "#", "+", "$", "=", "=", "/", "@", "%", "&", "-"]
partNumbersMultiplied = 0
foundNumbers = []  # Array of found numbers, consisting of the number and the index (123, (1,2))
numbersAndGears = []  # Array of numbers that are adjecent to gears

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
        elif currentChar.isdigit():
            currentDigit += currentChar  # Add found digit to the currentDigit
    if currentChar:  # Quite literal edge case where the number is on the edge of the matrix
        foundNumbers.append((currentDigit, (i, j-len(currentDigit))))


def checkForAdjecentGears(number):
    x, y = number[1][0], number[1][1]

    for i in range(x-1, x+2):  # Do I need to +2 because range is not inclusive?
        for j in range(y-1, (y+len(number[0])+1)):  # Took me way too long to figure out this should only be a +1
            if i < 0 or j < 0 or i >= len(schematic) or j >= len(schematic):  # Consider the bounds of the matrix
                continue
            if schematic[i][j] == "*":
                return (i, j)

    return None


def checkNumbersAndGears():
    global partNumbersMultiplied
    coordinateCount = {}

    for number, gearCoords in numbersAndGears:
        if gearCoords in coordinateCount:
            coordinateCount[gearCoords] += 1
        else:
            coordinateCount[gearCoords] = 1
    
    for coord, count in coordinateCount.items():
        result = 1
        if count == 2:
            for number, gearCoords in numbersAndGears:
                if gearCoords == coord:
                    result *= int(number)
            partNumbersMultiplied += result


for number in foundNumbers:
    gearCoordinates = checkForAdjecentGears(number)
    if gearCoordinates:
        numbersAndGears.append((number[0], gearCoordinates))


checkNumbersAndGears()
print(partNumbersMultiplied)