import pandas as pd
import numpy as np
from sklearn import model_selection
from sklearn import linear_model

data = pd.read_csv("Players.csv")
data = data[data["Chance of playing next round"] != 0]
data = data[data["Chance of playing next round"].notna()]

Midfielders = data[data["Position"] == 3]

Midfielders = Midfielders[["PPG", "Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT"]]

Total_Midfielders = Midfielders.shape[0]
Train = round((Total_Midfielders / 10) * 8)

Test_Midfielders = Midfielders[["Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT"]]

Test_Midfielders_PPG = Midfielders[["PPG"]]

Train_Midfielders_Data = Test_Midfielders.head(Train)
Train_Midfielders_Target = Test_Midfielders_PPG.head(Train)

Test_Midfielders_Data = Test_Midfielders.tail(Total_Midfielders - Train)
Test_Midfielders_Target = Test_Midfielders.tail(Total_Midfielders - Train)
kf = model_selection.KFold(n_splits=6,shuffle=True)

for train_index,test_index in kf.split(Train_Midfielders_Data.values):
    classifier = linear_model.LinearRegression()
    classifier.fit(Train_Midfielders_Data.values[train_index], Train_Midfielders_Target.values[train_index])
    # Predict_train = classifier.predict(Train_Midfeilders_Data.values[test_index])
    # print(metrics.accuracy_score(Train_Midfeilders_Target.values[test_index],Predict_train))
    test_predict = classifier.predict(Train_Midfielders_Data.values[test_index])
    print(np.concatenate((test_predict.reshape(len(test_predict),1), Train_Midfielders_Target.values[test_index].reshape(len(test_predict), 1)), 1))