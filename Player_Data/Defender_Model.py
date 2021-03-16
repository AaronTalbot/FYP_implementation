import pandas as pd
import numpy as np
from sklearn import model_selection
from sklearn import linear_model
from sklearn.metrics import mean_absolute_error

data = pd.read_csv("Players.csv")
# # data = data[data["Chance of playing next round"] != 0]
# data = data[data["Chance of playing next round"].notna()]

Defenders = data[data["Position"] == 2]

Defenders = Defenders[["Total points", "code", "Name", "Minutes Played", "Yellow Cards",
                                "Clean sheets", "Goals Conceded", "Assists", "PPG", "Fixture_Strength", "Chance of playing next round", "Result_Strength","GWPoints", "Predicted Points","Team", "Position","Live cost"]]

Total_Defenders = Defenders.shape[0]
Train = round((Total_Defenders /10)*8)

Test_Defenders = Defenders[["Total points", "Minutes Played", "Yellow Cards",
                                "Clean sheets", "Goals Conceded", "Assists","Result_Strength"]]

Test_Defenders_PPG = Defenders[["GWPoints"]]

Train_Defenders_Data = Test_Defenders.head(Train)
Train_Defenders_Target = Test_Defenders_PPG.head(Train)

Test_Defenders_Data = Test_Defenders.tail(Total_Defenders-Train)
Test_Defenders_Target = Test_Defenders_PPG.tail(Total_Defenders-Train)
kf = model_selection.KFold(n_splits=6,shuffle=True)
classifiers = []
accuracy = []
test_accuracies = []
count = 0

for train_index,test_index in kf.split(Train_Defenders_Data.values):
    classifier = linear_model.LinearRegression()
    classifier.fit(Train_Defenders_Data.values[train_index], Train_Defenders_Target.values[train_index])
    # Predict_train = classifier.predict(Train_Midfeilders_Data.values[test_index])
    # print(metrics.accuracy_score(Train_Midfeilders_Target.values[test_index],Predict_train))
    test_predict = classifier.predict(Train_Defenders_Data.values[test_index])
    print("Train Mean absolute error for split " + str(count) + " : ", mean_absolute_error(Train_Defenders_Target.values[test_index], test_predict))
    accuracy.append(mean_absolute_error(Train_Defenders_Target.values[test_index], test_predict))
    test_pred = classifier.predict(Test_Defenders_Data.values)
    print("Test  Mean absolute error for split " + str(count) + " : ",mean_absolute_error(Test_Defenders_Target.values,test_pred))
    test_accuracies.append(mean_absolute_error(Test_Defenders_Target.values,test_pred))
    classifiers.append(classifier)
    count += 1
    print("-"*50)

Future_Prediction = Defenders[["Total points", "Minutes Played", "Yellow Cards",
                                "Clean sheets", "Goals Conceded", "Assists","Fixture_Strength"]]

index = accuracy.index(min(accuracy))
predict = classifiers[index].predict(Future_Prediction.values)
Defenders["Predicted Points"] = predict.tolist()
Defenders["Live cost"] = Defenders["Live cost"]/10


Out = Defenders[["Name", "code", "Team", "Chance of playing next round", "Predicted Points","Live cost"]]

Out.to_csv("Defenders.csv")
