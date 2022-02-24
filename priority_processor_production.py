temp = input().split()
numLines = int(temp[0])
numSteps = int(temp[1])
lines = list()
for i in range(numLines):
    lines.append(input().split())
switchCosts = input().split()

def findSmallest(step, currentLine):
    smallest = int(lines[currentLine][step])
    smallestIndex = currentLine
    for i in range(numLines):
        if i != currentLine:
            adjusted = int(lines[i][step]) + int(switchCosts[step - 1])
            if adjusted < smallest:
                smallest = adjusted
                smallestIndex = i
    return smallestIndex

addLine = findSmallest(0, 0)
time = list()
time.append(int(lines[addLine][0]))
prodLine = "" + str(addLine)
i = 1
for i in range(numSteps):
    smallest = findSmallest(i, addLine)
    if smallest != addLine:
        time.append(int(lines[smallest][i]) + int(switchCosts[i - 1]))
        prodLine += " " + str(smallest)
        addLine = smallest
    else:
        time.append(int(lines[addLine][i]))
        prodLine += " " + str(addLine)

print(time[numSteps - 1])
print(prodLine)
    
    


