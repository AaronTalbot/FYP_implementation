import pandas as pd
import numpy as np
import math
from sklearn import model_selection
from sklearn import metrics
from sklearn import linear_model
from sklearn import svm
import matplotlib.pyplot as plt
import time

data = pd.read_csv("Players.csv")
data = data[data["Chance of playing next round"] != 0]
data = data[data["Chance of playing next round"].notna()]

Goalkeepers = data[data["Position"] == 1]
Defenders = data[data["Position"] == 2]
Midfielders = data[data["Position"] == 3]
Attackers = data[data["Position"] == 4]

Goalkeepers = Goalkeepers[["PPG", "Total points", "Minutes Played",
                                      "Clean sheets",
                                      "Goals Conceded", "Penalties Saved", "Yellow Cards", "Saves", "Bonus"]]
# Defenders = Defenders[Defenders["Total Points", "Minutes Played", "Percentage owned", "Minutes Played",
#                                 "Clean sheets", "Goals Conceded", "Assists", ""]]

Midfielders = Midfielders[["PPG", "Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT"]]

# print(Midfielders.shape[0])
# for i in range(0,121):
#     print(Midfielders.iloc[i])

Total_Midfeilders = Midfielders.shape[0]
Train = round((Total_Midfeilders /10)*8)
# print(Train)
Test_Midfeilders = Midfielders[["Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT"]]

Test_Midfeilders_PPG = Midfielders[["PPG"]]

kf = model_selection.KFold(n_splits=6,shuffle=True)

Train_Midfeilders_Data = Midfielders[["Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT"]]
Train_Midfeilders_Data = Train_Midfeilders_Data.head(Train)

Train_Midfeilders_Target = Midfielders[["PPG"]]

Train_Midfeilders_Target = Train_Midfeilders_Target.head(Train)

for train_index, test_index in kf.split(Train_Midfeilders_Data.values):
    classifier = linear_model.LinearRegression()
    classifier.fit(Train_Midfeilders_Data.values[train_index], Train_Midfeilders_Target.values[train_index])
    # Predict_train = classifier.predict(Train_Midfeilders_Data.values[test_index])
    # print(metrics.accuracy_score(Train_Midfeilders_Target.values[test_index],Predict_train))
    test_predict = classifier.predict(Train_Midfeilders_Data.values[test_index])
    print(np.concatenate((test_predict.reshape(len(test_predict),1),Train_Midfeilders_Target.values[test_index].reshape(len(test_predict),1)),1))
