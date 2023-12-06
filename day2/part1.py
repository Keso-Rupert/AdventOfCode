import re

inputFile = open("day2/input.txt", "r")

maxRed = 12
maxGreen = 13
maxBlue = 14

colors = ["red", "blue", "green"]

sumOfIds = 0
id = 1

for line in inputFile:
    # grabs = re.findall("(?<=:)([^;]+);([^;]+)(?:;([^;]+))?(?=.*$)", line.replace(",", "").strip("\n")) # Why tf is this a tuple
    grabs = line.split(":")[1].split(";")
    isPossible = True
    for grab in grabs:
        amountRed = 0
        amountGreen = 0
        amountBlue = 0
        index = 0

        words = grab.strip().replace(",", "").split(" ")

        for word in words:
            if word == "red":
                amountRed += int(words[index-1])
            elif word == "green":
                amountGreen += int(words[index-1])
            elif word == "blue":
                amountBlue += int(words[index-1])
            index += 1

        if (amountRed > maxRed) or (amountGreen > maxGreen) or (amountBlue > maxBlue):
            isPossible = False
        if not isPossible:
            break

    if isPossible:
        sumOfIds += id

    id +=1

print(sumOfIds)

        
