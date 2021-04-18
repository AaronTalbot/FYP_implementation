import pandas as pd


Team = [['Emiliano Martínez', 98980, 1, 5.3], ['Fabricio Agosto Ramírez', 40559, 1, 3.9], ['Matt Targett', 169359, 2, 5.1], ['Stuart Dallas', 87873, 2, 5.0], ['Aaron Cresswell', 55459, 2, 5.9], ['Luke Shaw', 106760, 2, 5.2], ['Rúben Santos Gato Alves Dias', 171314, 2, 6.1], ['James Ward-Prowse', 101178, 3, 5.9], ['Mohamed Salah', 118748, 3, 12.4], ['Phil Foden', 209244, 3, 6.0], ['Bruno Miguel Borges Fernandes', 141746, 3, 11.6], ['Raphael Dias Belloli', 219961, 3, 5.5], ['Patrick Bamford', 106617, 4, 6.7], ['Michail Antonio', 57531, 4, 6.6], ['Alexandre Lacazette', 59966, 4, 8.2]]


Defenders = pd.read_csv("Defenders.csv")
Midfielders = pd.read_csv("Midfeilders.csv")
Attackers = pd.read_csv("Attackers.csv")

Tot = [Defenders,Midfielders,Attackers]

All = pd.concat(Tot)


PP = []
for player in Team:
    for index, row in Defenders.iterrows():
        Code = row["code"]
        if Code == player[1]:
            Price = row["Predicted Points"]
            Price = Price.replace("[", "")
            Price = Price.replace("]", "")
            Price = float(Price)
            PP.append(Price)


res_max = max(PP,key=lambda x:float(x))


count = 0
for i in PP:
    if i == res_max:
        break;
    count+=1

Player = Team[count]
Name = Player[0]
print("Max player Predicted points is " + Name)
PP.pop(count-1)
Player.pop(count-1)
count_2 = 0
res_max2 = max(PP,key=lambda x:float(x))

for j in PP:
    if j == res_max2:
        break;
    count_2+=1

Player_2 = Team[count_2]
Name = Player_2[0]
print("Second Max player Predicted points is " + Name)


