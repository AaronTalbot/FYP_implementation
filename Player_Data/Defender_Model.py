import pandas as pd
import numpy as np
from sklearn import model_selection
from sklearn import linear_model

data = pd.read_csv("Players.csv")
data = data[data["Chance of playing next round"] != 0]
data = data[data["Chance of playing next round"].notna()]

Defenders = data[data["Position"] == 2]

Defenders = Defenders[["Total points", "Minutes Played", "Minutes Played", "Yellow Cards",
                                "Clean sheets", "Goals Conceded", "Assists", "PPG"]]

Total_Defenders = Defenders.shape[0]
Train = round((Total_Defenders /10)*8)

Test_Defenders = Defenders[["Total points", "Minutes Played", "Minutes Played", "Yellow Cards",
                                "Clean sheets", "Goals Conceded", "Assists"]]

Test_Defenders_PPG = Defenders[["PPG"]]

Train_Defenders_Data = Test_Defenders.head(Train)
Train_Defenders_Target = Test_Defenders_PPG.head(Train)

Test_Defenders_Data = Test_Defenders.tail(Total_Defenders-Train)
Test_Defenders_Target = Test_Defenders.tail(Total_Defenders-Train)
kf = model_selection.KFold(n_splits=6,shuffle=True)

for train_index,test_index in kf.split(Train_Defenders_Data.values):
    classifier = linear_model.LinearRegression()
    classifier.fit(Train_Defenders_Data.values[train_index], Train_Defenders_Target.values[train_index])
    # Predict_train = classifier.predict(Train_Midfeilders_Data.values[test_index])
    # print(metrics.accuracy_score(Train_Midfeilders_Target.values[test_index],Predict_train))
    test_predict = classifier.predict(Train_Defenders_Data.values[test_index])
    print(np.concatenate((test_predict.reshape(len(test_predict),1),Train_Defenders_Target.values[test_index].reshape(len(test_predict),1)),1))