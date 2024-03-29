import pandas as pd
import numpy as np
from sklearn import model_selection
from sklearn import linear_model
from sklearn.metrics import explained_variance_score
from sklearn.metrics import mean_absolute_error
data = pd.read_csv("Players.csv")
# data = data[data["Chance of playing next round"] != 0]
# data = data[data["Chance of playing next round"].notna()]

Attackers = data[data["Position"] == 4]

Attackers = Attackers[["PPG","Name", "code", "Total points", "Team", "Chance of playing next round", "Minutes Played", "Goals Scored", "Assists", "Yellow Cards", "Bonus", "ICT","Fixture_Strength", "Result_Strength","GWPoints", "Predicted Points", "Position","Live cost"]]

Total_Attackers = Attackers.shape[0]
Train = round((Total_Attackers / 10) * 8)
# print(Train)
# print(Total_Attackers)

Test_Attackers = Attackers[["PPG","Total points", "Minutes Played", "Goals Scored", "Assists", "Yellow Cards", "Bonus", "ICT","Result_Strength"]]

Test_Attackers_PPG = Attackers[["GWPoints"]]

Train_Attackers_Data = Test_Attackers.head(Train)
Train_Attackers_Target = Test_Attackers_PPG.head(Train)

Test_Attackers_Data = Test_Attackers.tail(Total_Attackers - Train)
Test_Attackers_Target = Test_Attackers_PPG.tail(Total_Attackers - Train)
kf = model_selection.KFold(n_splits=6,shuffle=True)
classifiers = []
accuracy = []
test_accuracies = []
count = 0
all_accuracies = []

for train_index,test_index in kf.split(Train_Attackers_Data.values):
    classifier = linear_model.LinearRegression()
    classifier.fit(Train_Attackers_Data.values[train_index], Train_Attackers_Target.values[train_index])
    test_predict = classifier.predict(Train_Attackers_Data.values[test_index])
    # print("Test variance score : ", explained_variance_score(Train_Attackers_Target.values[test_index],test_predict,multioutput='uniform_average'))
    print("Train Mean absolute error for split " + str(count) + " : ", mean_absolute_error(Train_Attackers_Target.values[test_index],test_predict))
    accuracy.append(mean_absolute_error(Train_Attackers_Target.values[test_index],test_predict))
    test_pred = classifier.predict(Test_Attackers_Data.values)
    print("Test  Mean absolute error for split " + str(count) + " : ", mean_absolute_error(Test_Attackers_Target.values, test_pred))
    test_accuracies.append(mean_absolute_error(Test_Attackers_Target.values, test_pred))

    all_predict = classifier.predict(Test_Attackers.values)
    print("Full data set accuracy for split "+ str(count)+ " : ", mean_absolute_error(Test_Attackers_PPG.values, all_predict))
    all_accuracies.append(mean_absolute_error(Test_Attackers_PPG.values, all_predict))
    classifiers.append(classifier)
    count += 1
    # print("-"*50)

Future_Prediction = Attackers[["PPG","Total points", "Minutes Played", "Goals Scored", "Assists", "Yellow Cards", "Bonus", "ICT","Fixture_Strength"]]
index = all_accuracies.index(min(all_accuracies))

predict = classifiers[index].predict(Future_Prediction.values)

# print(predict)

# print(predict.shape)
# print(Attackers.shape[0])
Attackers['Predicted Points'] = predict.tolist()
print(Attackers)
Attackers["Live cost"] = Attackers["Live cost"]/10
Out = Attackers[["Name", "code", "Team", "Chance of playing next round", "Predicted Points","Live cost"]]

Out.to_csv("Attackers.csv")
