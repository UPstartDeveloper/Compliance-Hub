from flask import Flask, flash, render_template, request, redirect, url_for
from pymongo import MongoClient
from bson.objectid import ObjectId
from werkzeug.utils import secure_filename
import os

app = Flask(__name__)

# Tell Flask how to find our database
host = os.environ.get('MONGODB_URI', 'mongodb://localhost:27017/Compliance')
client = MongoClient()
db = client.Compliance
documents = db.documents


@app.route('/')
def index():
    """User uploads file here."""
    return render_template("submission_new.html")


@app.route('/submission/form_tracker/upload', methods=["POST"])
def form_upload():
    """Add the uploaded file to the database."""

    if 'userFile' in request.files:
        # Make a new document JSON from form data
        file = request.files['userFile']
        file.save(file)

        new_doc = {
            "file_name": file.filename
        }

    # insert into PyMongo database
    documents.insert_one(new_doc)

    # return name of file to check it was uploaded ok
    return file.filename


if __name__ == '__main__':
    app.run(debug=True)
