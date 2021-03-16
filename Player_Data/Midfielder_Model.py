import pandas as pd
import numpy as np
from sklearn import model_selection
from sklearn import linear_model
from sklearn.metrics import mean_absolute_error

data = pd.read_csv("Players.csv")
# # data = data[data["Chance of playing next round"] != 0]
# data = data[data["Chance of playing next round"].notna()]

Midfielders = data[data["Position"] == 3]

Midfielders = Midfielders[["PPG", "Name","code","Chance of playing next round", "Team", "Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT","Fixture_Strength", "Result_Strength","GWPoints","Predicted Points","Live cost"]]

Total_Midfielders = Midfielders.shape[0]
Train = round((Total_Midfielders / 10) * 8)
# print(Total_Midfielders)
# print(Train)

Test_Midfielders = Midfielders[["Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT", "Result_Strength"]]

Test_Midfielders_PPG = Midfielders[["GWPoints"]]

Train_Midfielders_Data = Test_Midfielders.head(Train)
Train_Midfielders_Target = Test_Midfielders_PPG.head(Train)

Test_Midfielders_Data = Test_Midfielders.tail(Total_Midfielders - Train)
Test_Midfielders_Target = Test_Midfielders_PPG.tail(Total_Midfielders - Train)
kf = model_selection.KFold(n_splits=6,shuffle=True)
classifiers = []
accuracy = []
test_accuracies = []
count = 0
for train_index,test_index in kf.split(Train_Midfielders_Data.values):
    classifier = linear_model.LinearRegression()
    classifier.fit(Train_Midfielders_Data.values[train_index], Train_Midfielders_Target.values[train_index])
    # Predict_train = classifier.predict(Train_Midfeilders_Data.values[test_index])
    # print(metrics.accuracy_score(Train_Midfeilders_Target.values[test_index],Predict_train))
    classifiers.append(classifier)
    test_predict = classifier.predict(Train_Midfielders_Data.values[test_index])
    print("Train Mean absolute error for split " + str(count) + " : ", mean_absolute_error(Train_Midfielders_Target.values[test_index], test_predict))
    accuracy.append(mean_absolute_error(Train_Midfielders_Target.values[test_index], test_predict))
    test_pred = classifier.predict(Test_Midfielders_Data.values)
    print("Test  Mean absolute error for split " + str(count) + " : ",mean_absolute_error(Test_Midfielders_Target.values, test_pred))
    test_accuracies.append(mean_absolute_error(Test_Midfielders_Target.values, test_pred))
    count += 1
    print("-"*50)

Future_Prediction = Midfielders[["Total points", "Minutes Played", "Goals Scored", "Assists",
                                      "Clean sheets", "Yellow Cards", "Bonus", "ICT", "Fixture_Strength"]]

index = accuracy.index(min(accuracy))

predict = classifiers[index].predict(Future_Prediction.values)

Midfielders["Predicted Points"] = predict.tolist()
Midfielders["Live cost"] = Midfielders["Live cost"]/10
Out = Midfielders[["Name", "code", "Team", "Chance of playing next round", "Predicted Points","Live cost"]]

Out.to_csv("Midfeilders.csv")
