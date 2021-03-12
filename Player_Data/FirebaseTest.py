import firebase_admin
from firebase_admin import db
from firebase_admin import credentials
import pandas as pd
import numpy as np



def SetCredentials():
    cred = credentials.Certificate('final-year-project-dd795-firebase-adminsdk-ifhma-fa0b20c32c.json')

    firebase_admin.initialize_app(cred, {
        'databaseURL': 'https://final-year-project-dd795-default-rtdb.europe-west1.firebasedatabase.app'
    })


def writeDefenders():
    Defenders = pd.read_csv("Defenders.csv")
    ref = db.reference()
    Players_ref = ref.child("Players")
    Defenders_ref = Players_ref.child("Defenders")
    for index,row in Defenders.iterrows():
        if np.isnan(row["Chance of playing next round"]):
            Defenders_ref.child(row["Name"]).set({
                'Name': row["Name"],
                'code': row["code"],
                'Team': row["Team"],
                'Chance of playing next round' : '0',
                'Predicted Points': row["Predicted Points"]
            })
        else:
            Defenders_ref.child(row["Name"]).set({
                    'Name': row["Name"],
                    'code': row["code"],
                    'Team': row["Team"],
                    'Chance of playing next round': row["Chance of playing next round"],
                    'Predicted Points': row["Predicted Points"]
            })


def writeAttackers():
    Attackers = pd.read_csv("Attackers.csv")
    ref = db.reference()
    Players_ref = ref.child("Players")
    Attackers_ref = Players_ref.child("Attackers")
    for index,row in Attackers.iterrows():
        if np.isnan(row["Chance of playing next round"]):
            Attackers_ref.child(row["Name"]).set({

                'Name': row["Name"],
                'code': row["code"],
                'Team': row["Team"],
                'Chance of playing next round' : '0',
                'Predicted Points': row["Predicted Points"]
            })
        else:
            Attackers_ref.child(row["Name"]).set({
                    'Name': row["Name"],
                    'code': row["code"],
                    'Team': row["Team"],
                    'Chance of playing next round': row["Chance of playing next round"],
                    'Predicted Points': row["Predicted Points"]
            })

def writeMidfeilders():
    Midfeilders = pd.read_csv("Midfeilders.csv")
    ref = db.reference()
    Players_ref = ref.child("Players")
    Midfeilders_ref = Players_ref.child("Midfeilders")
    for index,row in Midfeilders.iterrows():
        if np.isnan(row["Chance of playing next round"]):
            Midfeilders_ref.child(row["Name"]).set({
                'Name': row["Name"],
                'code': row["code"],
                'Team': row["Team"],
                'Chance of playing next round' : '0',
                'Predicted Points': row["Predicted Points"]
            })
        else:
            Midfeilders_ref.child(row["Name"]).set({
                    'Name': row["Name"],
                    'code': row["code"],
                    'Team': row["Team"],
                    'Chance of playing next round': row["Chance of playing next round"],
                    'Predicted Points': row["Predicted Points"]
            })


SetCredentials()
writeDefenders()
writeAttackers()
writeMidfeilders()