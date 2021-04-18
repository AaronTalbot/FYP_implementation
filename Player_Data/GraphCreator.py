import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import os
import math

from sklearn import metrics

data = pd.read_csv("Players.csv")
Defenders = data[data["Position"] == 2]
Midfeilders = data[data["Position"] == 3]
Attackers = data[data["Position"] == 4]

Predicted_Defenders = pd.read_csv("Defenders.csv")
Predicted_Midfielders = pd.read_csv("Midfeilders.csv")
Predicted_Attackers = pd.read_csv("Attackers.csv")

Bruno = "Bruno Miguel Borges Fernandes"
Bamford = "Patrick Bamford"
Mings = "Tyrone Mings"


labels = ["Bruno Fernandes","Patrick Bamford","Tyrone Mings"]

Bruno_Prev = Midfeilders[Midfeilders["Name"]==Bruno]
Bruno_Predict = Predicted_Midfielders[Predicted_Midfielders["Name"]==Bruno]

Bamford_Prev = Attackers[Attackers["Name"]==Bamford]
Bamford_Predict = Predicted_Attackers[Predicted_Attackers["Name"]==Bamford]

Mings_Prev = Defenders[Defenders["Name"]==Mings]
Mings_Predict = Predicted_Defenders[Predicted_Defenders["Name"]==Mings]

Bruno_Prev_Points = int(Bruno_Prev["GWPoints"].values[0])
Bruno_Predict_Points = Bruno_Predict["Predicted Points"].values[0]
Bruno_Predict_Points = Bruno_Predict_Points.replace("[", "")
Bruno_Predict_Points = Bruno_Predict_Points.replace("]", "")
Bruno_Predict_Points = float(Bruno_Predict_Points)

Bamford_Prev_Points = int(Bamford_Prev["GWPoints"].values[0])
Bamford_Predict_Points = Bamford_Predict["Predicted Points"].values[0]
Bamford_Predict_Points = Bamford_Predict_Points.replace("[", "")
Bamford_Predict_Points = Bamford_Predict_Points.replace("]", "")
Bamford_Predict_Points = float(Bamford_Predict_Points)

Mings_Prev_points = int(Mings_Prev["GWPoints"].values[0])
Mings_Predict_Points = Mings_Predict["Predicted Points"].values[0]
Mings_Predict_Points = Mings_Predict_Points.replace("[", "")
Mings_Predict_Points = Mings_Predict_Points.replace("]", "")
Mings_Predict_Points = float(Mings_Predict_Points)

Predict_points = [round(Bruno_Predict_Points,2), round(Bamford_Predict_Points,2),round(Mings_Predict_Points,2)]
Actual_points = [Bruno_Prev_Points,Bamford_Prev_Points,Mings_Prev_points]

x = np.arange(len(labels))  # the label locations
width = 0.3  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, Predict_points, width, label='Predicted')
rects2 = ax.bar(x + width/2, Actual_points, width, label='Actual')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Points')
ax.set_title('Points Predicted v Actual')
ax.set_xticks(x)
ax.set_xticklabels(labels)
ax.legend()


def autolabel(rects):
    """Attach a text label above each bar in *rects*, displaying its height."""
    for rect in rects:
        height = rect.get_height()
        ax.annotate('{}'.format(height),
                    xy=(rect.get_x() + rect.get_width() / 2, height),
                    xytext=(0, 3),  # 3 points vertical offset
                    textcoords="offset points",
                    ha='center', va='bottom')



f = open("Gameweek.txt", "r")
for line in f:
    Gameweek = int(line)


autolabel(rects1)
autolabel(rects2)

fig.tight_layout()

# plt.show()
plt.savefig('GW' + str(Gameweek) + '.png')

Gameweek1 = int(Gameweek)
Gameweek1 += 1

f.close()



file = open("Gameweek.txt", "w")

file.write(str(Gameweek1))
file.close()

l = [Predicted_Defenders,Predicted_Attackers,Predicted_Midfielders]

Total = pd.concat(l)
Predict = []
Actual = []

