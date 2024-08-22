import re
import numpy

inputFile = open("day6/input.txt", "r")

times = re.findall("\d+", inputFile.readline())
distances = re.findall("\d+", inputFile.readline())
ways = 0

def calculateRaceDistance(holdTime, totalRaceTime):
    return holdTime * (totalRaceTime - holdTime)

raceTime = ""
distanceToBeat = ""

for time in times:
    raceTime += time
for distance in distances:
    distanceToBeat += distance

for j in range(1, int(raceTime)-1):
    raceDistance = calculateRaceDistance(j, int(raceTime))
    if raceDistance > int(distanceToBeat):
        ways += 1

print(ways)



