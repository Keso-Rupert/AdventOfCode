import re
import numpy

inputFile = open("day6/input.txt", "r")

times = re.findall("\d+", inputFile.readline())
distances = re.findall("\d+", inputFile.readline())

ways = []

def calculateRaceDistance(holdTime, totalRaceTime):
    return holdTime * (totalRaceTime - holdTime)

for i in range(0, len(times)):
    ways.append(0)
    raceTime = int(times[i])
    distance = int(distances[i])

    for j in range(1, int(raceTime)-1):
        raceDistance = calculateRaceDistance(j, raceTime)
        if raceDistance > distance:
            ways[i] += 1

print(numpy.prod(ways))