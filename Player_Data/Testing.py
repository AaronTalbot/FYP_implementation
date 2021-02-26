import asyncio
import aiohttp
import json
import pandas as pd
from fpl import FPL

def main():
    players = pd.read_csv("Players.csv")
    fixtures = pd.read_csv("Fixtures.csv")
    results = pd.read_csv("Results.csv")
    teams = pd.read_csv("Teams.csv")
    for i in range(1, 21):
        BoolResultAway = True
        Result_away_index = results[results["Away Team"]==i].index[-1]
        Result_home_index = results[results["Home Team"]==i].index[-1]
        if(Result_away_index > Result_home_index):
            index = Result_away_index
        else:
            index = Result_home_index
            BoolResultAway = False

        if(BoolResultAway):
            team = results["Home Team"].iloc[index]
        else:
            team = results["Away Team"].iloc[index]

        strength = teams[teams["ID"]==team]["strength_overall"].values
        players.loc[players.Team==i,"Result_Strength"]=strength

        BoolFixtureAway = True
        Fixture_away_index = fixtures[fixtures["Away Team"]==i].index[0]
        Fixture_home_index = fixtures[fixtures["Home Team"]==i].index[0]

        if (Fixture_away_index < Fixture_home_index):
            index2 = Fixture_away_index
        else:
            index2 = Fixture_home_index
            BoolFixtureAway = False

        if(BoolFixtureAway):
            team2 = fixtures["Home Team"].iloc[index2]
        else:
            team2 = fixtures["Away Team"].iloc[index2]

        strength2 = teams[teams["ID"] == team2]["strength_overall"].values
        players.loc[players.Team == i, "Fixture_Strength"] = strength2

    print(players.iloc[0])


    players.to_csv("Players.csv")


main()