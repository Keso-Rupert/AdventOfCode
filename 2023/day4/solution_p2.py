import re

inputFile = open("day4/input", "r").read().splitlines()

pointsP1 = 0
cardCounts = {}

for i in range(len(inputFile)):  # Initialize an cardCounts array with 1 card of everything
    cardCounts.update({i: 1})

for i, line in enumerate(inputFile):
    numbers = line.split("|")

    winningNumbers = set(numbers[0].split(":")[1].split()) # Had trouble with the regex ignoring the number before the colon (IE it would have 18 as a winning number for card 186:)
    selectedNumbers = set(re.findall("\d+", numbers[1]))
    matchedNumbers = winningNumbers.intersection(selectedNumbers)

    matches = len(matchedNumbers)

    if matches > 0:
        nrOfCards = cardCounts.get(i)  # Get the total amount of scratchcards for the current index
        
        for x in range(0, nrOfCards):  # For every instance of that scratchcard, check if we win even more!

            for j in range(1, matches+1):  # For every match, we add a subsequent card
                following = j+i
                if following >= len(inputFile):  # We cannot add scratchcards which we do not have
                    continue
                
                updatedCount = int(cardCounts.get(following)) + 1
                cardCounts.update({ following : updatedCount})  # Update cardCounts and add 1

pointsP2 = 0
for count in cardCounts.values():
    pointsP2 += count

print(pointsP2)