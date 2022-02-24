temp = input().split()
numLines = int(temp[0])
numSteps = int(temp[1])
lines = list()
for i in range(numLines):
    lines.append(input().split())
switchCosts = input().split()

def findSmallestEnds(step, currentLine):
    smallest = int(lines[currentLine][step])
    smallestIndex = currentLine
    for i in range(numLines):
        if i != currentLine:
            adjusted = int(lines[i][step]) + int(switchCosts[step - 1])
            if adjusted < smallest:
                smallest = adjusted
                smallestIndex = i
    return smallestIndex

def findSmallest(step, currentLine):
    if step == 0 or step >= numSteps - 2:
        return findSmallestEnds(step, currentLine)
    smallest = int(lines[currentLine][step]) + int(lines[currentLine][step + 1])
    smallestIndex = currentLine
    for i in range(numLines):
        if i != currentLine:
            adjusted = int(lines[i][step]) + int(switchCosts[step - 1]) + int(lines[i][step + 1])
            if adjusted < smallest:
                smallest = adjusted
                smallestIndex = i
    return smallestIndex

addLine = findSmallest(0, 0)
time = list()
time.append(int(lines[addLine][0]))
prodLine = "" + str(addLine + 1)
for i in range(1, numSteps):
    smallest = findSmallest(i, addLine)
    if smallest != addLine:
        time.append(time[i - 1] + int(lines[smallest][i]) + int(switchCosts[i - 1]))
        prodLine += " " + str(smallest + 1)
        addLine = smallest
    else:
        time.append(time[i - 1] + int(lines[addLine][i]))
        prodLine += " " + str(addLine + 1)

print(time[numSteps - 1])
print(prodLine)
    
    


