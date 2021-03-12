# Import database module.
from firebase_admin import db

# Get a database reference to our blog.
ref = db.reference('https://final-year-project-dd795-default-rtdb.europe-west1.firebasedatabase.app/')

users_ref = ref.child('Players')
users_ref.set({
    'alanisawesome': {
        'date_of_birth': 'June 23, 1912',
        'full_name': 'Alan Turing'
    },
    'gracehop': {
        'date_of_birth': 'December 9, 1906',
        'full_name': 'Grace Hopper'
    }
})