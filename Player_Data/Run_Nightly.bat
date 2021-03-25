@echo off
python GatherPlayerFiles.py
python Defender_Model.py
python Midfielder_Model.py
python Attacker_Model.py
python FirebaseTest.py