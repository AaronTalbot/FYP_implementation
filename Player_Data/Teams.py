from fpl import FPL
import aiohttp
import asyncio
import pandas as pd

async def main():
     async with aiohttp.ClientSession() as session:
         fpl = FPL(session)
         array = []
         for j in range(1,21):
            team = await fpl.get_team(j)
            id = team.id
            name = team.name
            short_name = team.short_name
            strength_overall = ((team.strength_overall_home + team.strength_overall_away)/2)

            arr = [id,name,short_name,strength_overall,]
            array.append(arr)
         data = pd.DataFrame.from_records(array)
         data.columns = ["ID", "Name", "Short_Name", "strength_overall"]
         data.to_csv("Teams.csv")

asyncio.run(main())