from datetime import datetime as dt


'''
inspired by (https://stackoverflow.com/questions/26036335/how-to-store-upload
ed-file-in-mongodb-in-flask#32720524)
'''


class FormDocument(mongoDb.Document):
    """Initialize file upload as an mongodb Document object."""
    created = mongoDb.DateTimeField(default=dt.utcnow, required=True)
    filename = mongoDb.StringField()
    _file = mongoDb.FileField()
