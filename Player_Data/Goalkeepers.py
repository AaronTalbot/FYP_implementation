import pandas as pd


data = pd.read_csv("Players.csv")
# data = data[data["Chance of playing next round"] != 0]
# data = data[data["Chance of playing next round"].notna()]

Goalkeepers = data[data["Position"] == 1]

Out = Goalkeepers[["Name", "code", "Team", "Chance of playing next round"]]


Out.to_csv("Goalkeepers.csv")