import re

inputFile = open("day9/input.txt", "r")

def findNextSequence(sequence):   
    nextSequence = []

    for i in range(0, len(sequence)-1 ):
        nextSequence.append(int(sequence[i+1]) - int(sequence[i]) )
    
    return nextSequence


def findFirstDecrementation(sequence):
    for i in range(len(sequence), 1, -1):
        sequence[i-2].insert(0, int(sequence[i-2][0]) - int(sequence[i-1][0]))

    return int(sequence[0][0]) 

sumOfIncrements = 0


for line in inputFile:
    currentSequence = re.findall("-?\d+", line)
    print(currentSequence)

    sequences = []
    sequences.append(currentSequence)
    increments = []
    depth = 0
    while True:
        sequences.append(findNextSequence(sequences[-1]))
                
        if all(number == 0 for number in sequences[-1]):
            break
    
    sumOfIncrements += findFirstDecrementation(sequences)

print(sumOfIncrements)
