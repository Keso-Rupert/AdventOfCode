import math

inputFile = open("input.txt", "r")

instructions = inputFile.readline().strip().replace("R", "1").replace("L", "0")

navigationDict = {}
nodeKeys = []
cycles = []

for line in inputFile:
    if not line.strip():  # Skip first empty line
        continue

    line = line.strip().replace("(", "").replace(")", "").replace(" ", "")  # Get rid of some BS characters

    node, elementsStr = line.split("=")
    elements = elementsStr.split(",")

    if node.endswith("A"): nodeKeys.append(node)
    navigationDict.update({node: elements})


def solveLoop(nodeToSolve):
    cycle = []
    notFound = True
    instructionsIndex = 0
    steps = 0
    current = nodeToSolve

    while notFound:
        if instructionsIndex >= len(instructions): instructionsIndex = 0  # Reset the index to loop through instructions

        if current.endswith("Z"):  # Condition to end
            break

        current = navigationDict[current][int(instructions[instructionsIndex])]
        instructionsIndex += 1
        steps += 1

    return steps


for currentNode in nodeKeys:
    cycles.append(solveLoop(currentNode))

result = math.lcm(*cycles)

print(result)