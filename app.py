from flask import Flask, flash, render_template, request, redirect, url_for
from pymongo import MongoClient
from bson.objectid import ObjectId
from werkzeug.utils import secure_filename
import os
import zipfile
# from document import FormDocument

app = Flask(__name__)

# Tell Flask how to find our database
host = os.environ.get('MONGODB_URI', 'mongodb://localhost:27017/Compliance')
client = MongoClient()
db = client.Compliance
documents = db.documents

# MOCK array of requirements to fill out, and image links to represent each
links = [
    "https://accidentattorneys.org/wp-content/uploads/2015/03/13080769_l.jpg",
    "https://www.myairbags.com/wp-content/uploads/2018/07/red.jpg",
    "http://img1.photo138.com/MS1/ZJ6396700-C-4-9.jpg",
    "https://cdn-images-1.medium.com/max/1200/1*Ef6QU6hyuyvvqVfl87a1qw.png"
]
requirements = [
    {"name": "Regulation A",
     "description": "You must make sure the airbags are working.",
     "image": links[0]},
    {"name": "Regulation B",
     "description": "You better have seatbelts.",
     "image": links[1]},
    {"name": "Regulation C",
     "description": "Don't forget forget about my cupholders!",
     "image": links[2]},
    {"name": "Regulation D",
     "description": "Make sure that self-driving algorithm values human life!",
     "image": links[3]}
]


@app.route('/')
def show_requirements():
    """Show user all the requireements that need to be completed."""
    return render_template("requirements_index.html",
                           requirements=requirements,
                           links=links)


@app.route('/submissions/form_tracker')
def form_upload():
    """User uploads file here."""
    return render_template("submission_new.html",
                           requirements=requirements)


@app.route('/submission/form_tracker/upload', methods=["POST"])
def form_new():
    """Add the uploaded file to the database."""

    if 'userFile' in request.files:
        '''
        file = request.files['userFile']
        file_name = secure_filename(file.filename)
        doc = FormDocument()
        doc.filename = file_name
        return "This works so far"

        '''
        # Make a new document JSON from form data
        file = request.files['userFile']
        file.save(file)

        new_doc = {
            "file_name": file.filename,
            "requirement": request.form.get('requirement')
        }

        # insert into PyMongo database
        documents.insert_one(new_doc)

        # return name of file to check it was uploaded ok
        return redirect(url_for('form_upload'))


@app.route('/submission/download_zip')
def get_zip():
    """Takes all files in database and return a downloadable ZIP folder.
    V1: this is first iteration, just serving file back to user using POST"""
    return render_template("submission_complete.html")


if __name__ == '__main__':
    app.run(debug=True)
