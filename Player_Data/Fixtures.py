from fpl import FPL
import aiohttp
import asyncio
import pandas as pd
async def main():
    async with aiohttp.ClientSession() as session:
        fpl = FPL(session)
        Results_array = []
        Fixtures_array = []
        unconfirmed_fixrtures = [180,286, 317]
        for i in range(1, 381):
            if(i in unconfirmed_fixrtures):
                pass
            else:
                fixture = await fpl.get_fixture(i)
                id = fixture.id
                team_a = fixture.team_a
                team_h = fixture.team_h
                if(fixture.finished):
                    team_a_score = fixture.team_a_score
                    team_h_score = fixture.team_h_score
                    result = [id,team_a,team_a_score,team_h,team_h_score]
                    Results_array.append(result)
                else:
                    fixtures = [id,team_a,team_h]
                    Fixtures_array.append(fixtures)
        data = pd.DataFrame.from_records(Results_array)
        data2 = pd.DataFrame.from_records(Fixtures_array)
        data.columns = ["ID", "Away Team", "Away Team Score", "Home Team", "Home Team Score" ]
        data2.columns = ["ID", "Away Team", "Home Team"]
        data.to_csv("Results.csv")
        data2.to_csv("Fixtures.csv")





asyncio.get_event_loop().run_until_complete(main())