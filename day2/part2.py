inputFile = open("input.txt", "r")

maxRed = 12
maxGreen = 13
maxBlue = 14

colors = ["red", "blue", "green"]

sumOfIds = 0
id = 1

for line in inputFile:
    grabs = line.split(":")[1].split(";")
    powerOf = 0
    amountRed = 0
    amountGreen = 0
    amountBlue = 0

    for grab in grabs:

        index = 0

        words = grab.strip().replace(",", "").split(" ")

        for word in words:
            if word == "red":
                if amountRed < int(words[index - 1]):
                    amountRed = int(words[index - 1])
            elif word == "green":
                if amountGreen < int(words[index - 1]):
                    amountGreen = int(words[index - 1])
            elif word == "blue":
                if amountBlue < int(words[index - 1]):
                    amountBlue = int(words[index - 1])

            index += 1

    # print(amountRed, amountGreen, amountBlue)
    powerOf = amountRed * amountGreen * amountBlue

    sumOfIds += powerOf

    id += 1

print(sumOfIds)


