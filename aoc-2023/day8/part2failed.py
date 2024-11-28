
inputFile = open("input.txt", "r")

instructions = inputFile.readline().strip().replace("R", "1").replace("L", "0")

navigationDict = {}
nodeKeys = {}

for line in inputFile:
    if not line.strip():  # Skip first empty line
        continue

    line = line.strip().replace("(", "").replace(")", "").replace(" ", "")  # Get rid of some BS characters

    node, elementsStr = line.split("=")
    elements = elementsStr.split(",")

    if node.endswith("A"): nodeKeys.update({node : ""})
    navigationDict.update({node: elements})


# What do we need to do?
# Get a list of all keys ending with A
# For each key in the list, execute one instruction, get the next key
# Check all received keys if they end with the Z, if the do, print steps

instructionsIndex = 0
steps = 0

while True:
    if instructionsIndex >= len(instructions): instructionsIndex = 0  # Reset the index to loop through instructions

    for key in nodeKeys.keys():  # For each key, get the next key with the instructions
        nodeKeys[key] = navigationDict[key][int(instructions[instructionsIndex])]

    steps += 1

    allEndInZ = True

    for foundKey in nodeKeys.values():
        if not foundKey.endswith("Z"):
            allEndInZ = False

    if allEndInZ:
        print(steps)
        break

    for oldKey in list(nodeKeys.keys()):  # Replace the existing keys with the new keys since no resolution was found
        newKey = navigationDict[oldKey][int(instructions[instructionsIndex])]
        nodeKeys[newKey] = nodeKeys.pop(oldKey)

    instructionsIndex += 1