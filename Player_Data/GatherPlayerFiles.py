import asyncio
import aiohttp
import json
import pandas as pd
from fpl import FPL
from prettytable import PrettyTable


async def main():
    async with aiohttp.ClientSession() as session:
        fpl = FPL(session)
        players = await fpl.get_players()
    # with open("data.txt", 'w') as outfile:
    #     json.dump(players,outfile)
    player_table = PrettyTable()
    player_table.field_names = ["Player", "£", "G", "A", "code", "id"]
    player_table.align["Player"] = "l"

    GK_Table = PrettyTable()
    GK_Table.field_names = ["Player", "Saves", "Pen Saves", "CS", "PP90"]
    GK_Table.align["Player"] = "l"

    DEF_Table = PrettyTable()
    DEF_Table.field_names = ["Player", "£", "G", "A", "G + A", "PP90"]
    DEF_Table.align["Player"] = "l"

    MID_TABLE = PrettyTable()
    MID_TABLE.field_names = ["Player", "£", "G", "A", "G + A", "PP90", ]
    MID_TABLE.align["Player"] = "l"

    ATT_Table = PrettyTable()
    ATT_Table.field_names = ["Player", "£", "G", "A", "G + A", "PP90", ]
    ATT_Table.align["Player"] = "l"
    goalkeepers = []
    defenders = []
    midfeilders = []
    attackers = []

    array = []
    for i in players:

        full_name = i.first_name + " " + i.second_name
        Chance_of_Playing = i.chance_of_playing_next_round
        Chance_of_playing_t_R = i.chance_of_playing_this_round
        code = i.code
        position = i.element_type
        cost = i.now_cost
        PPG = i.points_per_game
        Per = i.selected_by_percent
        team = i.team
        total_points = i.total_points
        mins = i.minutes
        Goals = i.goals_scored
        Assists = i.assists
        CS = i.clean_sheets
        goals_conceded = i.goals_conceded
        penalties_saved = i.penalties_saved
        penalties_missed = i.penalties_missed
        yellow_cards = i.yellow_cards
        saves = i.saves
        bonus = i.bonus
        ICT = i.ict_index
        arr = [full_name, Chance_of_Playing, Chance_of_playing_t_R, code, position, cost, PPG, Per, team, total_points, mins, Goals, Assists, CS, goals_conceded
                  , penalties_saved , penalties_missed, yellow_cards, saves, bonus, ICT]
        array.append(arr)

    data = pd.DataFrame.from_records(array)
    data.columns = ["Name", "Chance of playing this round", "Chance of playing next round", "code", "Position", "Live cost", "PPG"
                    , "Percentage owned", "Team", "Total points", "Minutes Played", "Goals Scored", "Assists", "Clean sheets",
                     "Goals Conceded", "Penalties Saved", "Penalties Missed", "Yellow Cards", "Saves", "Bonus", "ICT"]

    data.to_csv("Players.csv")
    for i in array:
        print(i)


if __name__ == "__main__":
    asyncio.run(main())
