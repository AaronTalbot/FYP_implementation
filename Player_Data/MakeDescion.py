import pandas as pd

Teams = ["Arsenal", "Aston Villa", "Brighton", "Burnley", "Chelsea","Crystal Palace", "Everton", "Fulham",
         "Leicester", "Leeds", "Liverpool", "Manchester City", "Manchester United", "Newcastle", "Sheffield", "Southampton",
         "Tottenham", "West Brom", "West Ham", "Wolves"]


Goalkeepers = pd.read_csv("Goalkeepers.csv")
Defenders = pd.read_csv("Defenders.csv")
Midfielders = pd.read_csv("Midfeilders.csv")
Attackers = pd.read_csv("Attackers.csv")

Team_Count = [1,2,0,0,0,0,0,1,0,3,1,2,2,0,0,1,0,0,2,0]

Team = []
# itb = float(input("How much money do you have itb?:>>"))
itb= 0.3
# for i in range(2):
#     Player = []
#     for index,team in enumerate(Teams):
#         print(str(index+1) + ": " + team)
#     T = int(input("What team is the goalkeeper in?:>>"))
#     Team_Count[T-1] += 1
#     Goalkeepers_t = Goalkeepers[Goalkeepers["Team"]==T]
#     enum = 0
#     for index, row in Goalkeepers_t.iterrows():
#         print(str(enum) + ": " +  row["Name"])
#         enum+=1
#     G = int(input("What number is the goalkeeper?:>>"))
#     Sell_Price = float(input("What is his sell price(e.g 4.3):>>"))
#     p = Goalkeepers_t.iloc[G]
#     Player.append(p["Name"])
#     Player.append(p["code"])
#     Player.append(1)
#     Player.append(Sell_Price)
#     Team.append(Player)
#
#
# for i in range(5):
#     Player = []
#     for index,team in enumerate(Teams):
#         print(str(index+1) + ": " + team)
#     T = int(input("What team is the defender in?:>>"))
#     Team_Count[T-1] += 1
#     defender_t = Defenders[Defenders["Team"]==T]
#     enum = 0
#     for index, row in defender_t.iterrows():
#         print(str(enum) + ": " +  row["Name"])
#         enum+=1
#     G = int(input("What number is the defender?:>>"))
#     Sell_Price = float(input("What is his sell price(e.g 4.3):>>"))
#     p = defender_t.iloc[G]
#     Player.append(p["Name"])
#     Player.append(p["code"])
#     Player.append(2)
#     Player.append(Sell_Price)
#     Team.append(Player)
#
# for i in range(5):
#     Player = []
#     for index,team in enumerate(Teams):
#         print(str(index+1) + ": " + team)
#     T = int(input("What team is the midfielder in?:>>"))
#     Team_Count[T-1] += 1
#     midfielder_t = Midfielders[Midfielders["Team"]==T]
#     enum = 0
#     for index, row in midfielder_t.iterrows():
#         print(str(enum) + ": " +  row["Name"])
#         enum+=1
#     G = int(input("What number is the midfielder?:>>"))
#     Sell_Price = float(input("What is his sell price(e.g 4.3):>>"))
#     p = midfielder_t.iloc[G]
#     Player.append(p["Name"])
#     Player.append(p["code"])
#     Player.append(3)
#     Player.append(Sell_Price)
#     Team.append(Player)
#
# for i in range(3):
#     Player = []
#     for index,team in enumerate(Teams):
#         print(str(index+1) + ": " + team)
#     T = int(input("What team is the attacker in?:>>"))
#     Team_Count[T-1] += 1
#     attacker_t = Attackers[Attackers["Team"]==T]
#     enum = 0
#     for index, row in attacker_t.iterrows():
#         print(str(enum) + ": " +  row["Name"])
#         enum+=1
#     G = int(input("What number is the attacker?:>>"))
#     Sell_Price = float(input("What is his sell price(e.g 4.3):>>"))
#     p = attacker_t.iloc[G]
#     Player.append(p["Name"])
#     Player.append(p["code"])
#     Player.append(4)
#     Player.append(Sell_Price)
#     Team.append(Player)


Team = [['Emiliano Martínez', 98980, 1, 5.3], ['Fabricio Agosto Ramírez', 40559, 1, 3.9], ['Matt Targett', 169359, 2, 5.0], ['Stuart Dallas', 87873, 2, 5.0], ['Aaron Cresswell', 55459, 2, 5.9], ['Luke Shaw', 106760, 2, 5.2], ['Rúben Santos Gato Alves Dias', 171314, 2, 6.1], ['James Ward-Prowse', 101178, 3, 5.9], ['Mohamed Salah', 118748, 3, 12.5], ['Phil Foden', 209244, 3, 6.0], ['Bruno Miguel Borges Fernandes', 141746, 3, 11.6], ['Raphael Dias Belloli', 219961, 3, 5.5], ['Patrick Bamford', 106617, 4, 6.6], ['Michail Antonio', 57531, 4, 6.5], ['Alexandre Lacazette', 59966, 4, 8.2]]

Def = []
Def_Dif = []
Mid = []
Mid_Dif = []
Att = []
Att_Dif = []
for player in Team:

    if player[2]== 2:
        code = player[1]
        PP = Defenders.loc[Defenders['code']==code]['Predicted Points'].values
        p = PP[0]
        p = p.replace("[","")
        p = p.replace("]","")
        p = float(p)
        T = Defenders.loc[Defenders['code']==code]["Team"].values
        Team_enum = T[0]
        Difference = 0

        Team_Count[Team_enum-1] = Team_Count[Team_enum-1]-1
        temp_row = None
        iterator = 0
        for index,row in Defenders.iterrows():
            budget = row["Live cost"] - itb
            Price = row["Predicted Points"]
            Price = Price.replace("[", "")
            Price = Price.replace("]", "")
            Price = float(Price)
            if budget <= player[3]:
                if Difference == 0:
                    if(p<Price) and (Team_Count[row["Team"]-1] <3):
                        temp_row = row
                        Difference = Price - p
                else:
                    if(p<Price) and (Team_Count[row["Team"]-1] <3) and (Price - p > Difference):
                        temp_row = row
                        Difference = Price - p
        Def.append(temp_row)
        Def_Dif.append(Difference)
        Team_Count[Team_enum - 1] = Team_Count[Team_enum - 1] + 1
    #     =========================================================================================================================
    elif player[2] == 3:
        code = player[1]
        PP = Midfielders.loc[Midfielders['code'] == code]['Predicted Points'].values
        p = PP[0]
        p = p.replace("[", "")
        p = p.replace("]", "")
        p = float(p)
        T = Midfielders.loc[Midfielders['code'] == code]["Team"].values
        Team_enum = T[0]

        Team_Count[Team_enum - 1] = Team_Count[Team_enum - 1] - 1
        Difference = 0
        temp_row = None
        for index, row in Midfielders.iterrows():
            budget = row["Live cost"] - itb
            Price = row["Predicted Points"]
            Price = Price.replace("[", "")
            Price = Price.replace("]", "")
            Price = float(Price)
            if budget <= player[3]:
                if Difference == 0:
                    if (p < Price) and (Team_Count[row["Team"] - 1] < 3):
                        temp_row = row
                        Difference = Price - p
                else:
                    if (p < Price) and (Team_Count[row["Team"] - 1] < 3) and (Price - p > Difference):
                        temp_row = row
                        Difference = Price - p
        Mid.append(temp_row)
        Mid_Dif.append(Difference)
        Team_Count[Team_enum - 1] = Team_Count[Team_enum - 1] + 1


    #     ==========================================================================================================================
    elif player[2] == 4:
        code = player[1]
        PP = Attackers.loc[Attackers['code'] == code]['Predicted Points'].values
        p = PP[0]
        p = p.replace("[", "")
        p = p.replace("]", "")
        p = float(p)
        T = Attackers.loc[Attackers['code'] == code]["Team"].values
        Team_enum = T[0]

        Team_Count[Team_enum - 1] = Team_Count[Team_enum - 1] - 1
        Difference = 0
        temp_row = None
        for index, row in Attackers.iterrows():
            budget = row["Live cost"] - itb
            Price = row["Predicted Points"]
            Price = Price.replace("[", "")
            Price = Price.replace("]", "")
            Price = float(Price)
            if budget <= player[3]:
                if Difference == 0:
                    if (p < Price) and (Team_Count[row["Team"] - 1] < 3):
                        temp_row = row
                        Difference = Price - p
                else:
                    if (p < Price) and (Team_Count[row["Team"] - 1] < 3) and (Price - p > Difference):
                        temp_row = row
                        Difference = Price - p
        Att.append(temp_row)
        Att_Dif.append(Difference)
        Team_Count[Team_enum - 1] = Team_Count[Team_enum - 1] + 1


T_Def= []
T_Mid = []
T_Attackers = []
for i,player in enumerate(Team):
    if(i>=2 and i<7):
        T_Def.append(player)
    elif(i>=7 and i <=11):
        T_Mid.append(player)
    elif(i>11):
        T_Attackers.append(player)

for inde,player in enumerate(Def):
    if player is not None:
        print(T_Def[inde][0] + " -> " + player["Name"] + ": " + str(Def_Dif[inde]))

for index,player in enumerate(Mid):
    if player is not None:
        print(T_Mid[index][0] + " -> " + player["Name"] + ": " + str(Mid_Dif[index]))

for ind,player in enumerate(Att):
    if player is not None:
        print(T_Attackers[ind][0] + " -> " + player["Name"] + ": " + str(Att_Dif[ind]))