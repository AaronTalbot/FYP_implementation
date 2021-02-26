import pandas as pd
import numpy as np
from sklearn import model_selection
from sklearn import linear_model

data = pd.read_csv("Players.csv")
data = data[data["Chance of playing next round"] != 0]
data = data[data["Chance of playing next round"].notna()]

Attackers = data[data["Position"] == 4]

Attackers = Attackers[["PPG", "Total points", "Minutes Played", "Goals Scored", "Assists", "Yellow Cards", "Bonus", "ICT"]]

Total_Attackers = Attackers.shape[0]
Train = round((Total_Attackers / 10) * 8)
print(Train)
print(Total_Attackers)

Test_Attackers = Attackers[["Total points", "Minutes Played", "Goals Scored", "Assists", "Yellow Cards", "Bonus", "ICT"]]

Test_Attackers_PPG = Attackers[["PPG"]]

Train_Attackers_Data = Test_Attackers.head(Train)
Train_Attackers_Target = Test_Attackers_PPG.head(Train)

Test_Attackers_Data = Test_Attackers.tail(Total_Attackers - Train)
Test_Attackers_Target = Test_Attackers.tail(Total_Attackers - Train)
kf = model_selection.KFold(n_splits=6,shuffle=True)
Accuracy = []
#
# for train_index,test_index in kf.split(Train_Attackers_Data.values):
#     classifier = linear_model.LinearRegression()
#     # classifier2 =
#     classifier.fit(Train_Attackers_Data.values[train_index], Train_Attackers_Target.values[train_index])
#     test_predict = classifier.predict(Train_Attackers_Data.values[test_index])
#     print(test_predict)
#     print(Train_Attackers_Target.values[test_index])
#     print("-"*50)
#     AccuracyTrain = abs(test_predict - Train_Attackers_Target.values[test_index])
#     Accuracy.append(AccuracyTrain)
#
# print(Accuracy)
