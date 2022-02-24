temp = int(input().split())
numLines = temp[0]
numSteps = temp[1]
lines = list()
for i in range(numLines):
    lines.append(int(input().split()))
switchCosts = int(input().split())

def findSmallest(step, currentLine):
    smallest = lines[currentLine][step]
    smallestIndex = currentLine
    for i in range(numLines):
        if i != currentLine:
            adjusted = lines[i][step] + switchCosts[step - 1]
            if adjusted < smallest:
                smallest = adjusted
                smallestIndex = i
    return smallestIndex

addLine = findSmallest(0, 0)
time = list()
time.append(lines[addLine][0])
prodLine = "" + addLine
i = 1
for i in range(numSteps):
    temp = findSmallest(i, addLine)
    if temp != addLine:
        time.append(lines[temp][i] + switchCosts[i - 1])
        prodLine += " " + temp
        addLine = temp
    else:
        time.append(lines[addLine][i])
        prodLine += " " + addLine

print(time[numSteps - 1])
print(prodLine)
    
    


