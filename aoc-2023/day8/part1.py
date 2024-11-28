
inputFile = open("input.txt", "r")

instructions = inputFile.readline().strip().replace("R", "1").replace("L", "0")

navigationDict = {}

for line in inputFile:
    if not line.strip():  # Skip first empty line
        continue

    line = line.strip().replace("(", "").replace(")", "").replace(" ", "")  # Get rid of some BS characters

    node, elementsStr = line.split("=")
    elements = elementsStr.split(",")

    navigationDict.update({node: elements})


key = "AAA"
notFound = True
instructionsIndex = 0
steps = 0

while notFound:
    if instructionsIndex >= len(instructions): instructionsIndex = 0  # Reset the index to loop through instructions

    if key == "ZZZ":  # Condition to end
        print(steps)
        break

    key = navigationDict[key][int(instructions[instructionsIndex])]
    instructionsIndex += 1
    steps += 1