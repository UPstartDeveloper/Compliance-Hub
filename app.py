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
requirements = db.requirements

# array of requirements to fill out, and image links to represent each
links = [
    "https://accidentattorneys.org/wp-content/uploads/2015/03/13080769_l.jpg",
    "https://www.myairbags.com/wp-content/uploads/2018/07/red.jpg",
    "http://img1.photo138.com/MS1/ZJ6396700-C-4-9.jpg",
    "https://cdn-images-1.medium.com/max/1200/1*Ef6QU6hyuyvvqVfl87a1qw.png"
]
list_of_requirements = [
    {"name": "Regulation A",
     "description": "You must make sure the airbags are working.",
     "image": links[0],
     "num_required": 3,
     "documents": list(),
     "num_submitted": 0},
    {"name": "Regulation B",
     "description": "You better have seatbelts.",
     "image": links[1],
     "num_required": 3,
     "documents": list(),
     "num_submitted": 0},
    {"name": "Regulation C",
     "description": "Don't forget forget about my cupholders!",
     "image": links[2],
     "num_required": 3,
     "documents": list(),
     "num_submitted": 0},
    {"name": "Regulation D",
     "description": "Make sure that self-driving algorithm values human life!",
     "image": links[3],
     "num_required": 3,
     "documents": list(),
     "num_submitted": 0}
]

# requirements.remove()
# requirements.insert(list_of_requirements)  # adds requirements to DB
# documents.remove()


@app.route('/')
def show_requirements():
    """Show user all the requireements that need to be completed."""
    return render_template("requirements_index.html",
                           requirements=requirements.find(),
                           links=links,
                           documents=documents.find())


@app.route('/submissions/form_tracker')
def form_upload():
    """User uploads file here."""
    return render_template("submission_new.html",
                           requirements=requirements.find())


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
        doc_id = documents.insert_one(new_doc).inserted_id
        # add the id of the new document to the
        # list of its corresponding requirement, and increment num_submitted
        requirement = requirements.find_one({"name": new_doc["requirement"]})
        list_of_docs = requirement["documents"]
        list_of_docs.append(doc_id)
        new_num = requirement["num_submitted"] + 1
        requirements.update_one({"name": new_doc["requirement"]},
                                {"$set": {
                                 "documents": list_of_docs,
                                 "num_submitted": new_num}})
        # redirect to the requirement page for which document was submitted
        return redirect(url_for('requirement_show',
                        requirement_id=requirement.get('_id')))


@app.route('/submissions/<requirement_id>')
def requirement_show(requirement_id):
    """Show a single requirement and documents submitted for it."""
    # document = documents.find_one({"_id": ObjectId(document_id)})
    requirement = requirements.find_one({'_id': ObjectId(requirement_id)})
    return render_template("requirement_show.html",
                           requirement=requirement,
                           documents=documents.find())


@app.route("/submissions/<requirement_id>/edit")
def requirement_edit(requirement_id):
    """Show the edit form for documents listed under a requirement."""
    requirement = requirements.find_one({"_id": ObjectId(requirement_id)})
    documents_to_change = documents.find({"requirement":
                                         requirement.get("name")})
    # url_to_edit_doc = lookup_url('submission_update')
    return render_template("submission_edit.html",
                           documents=documents_to_change,
                           requirement=requirement,
                           requirement_id=requirement_id)


@app.route("/submissions/edit?")
def submission_update():
    """Redirect to a form to upload a new file,
       for the document user selected to change."""
    # find out which requirement this submission goes under
    document_id = request.args.get("document")
    document = documents.find_one({"_id": ObjectId(document_id)})
    requirement = requirements.find_one({"name": document.get("requirement")})
    '''
    # create an updated document
    file = request.files['userFile']
    file.save(file)
    # update the documents database
    documents.update_one(
        {'_id': ObjectId(document_id)},
        {"$set": {"file_name": file.filename}})
    '''
    '''
    # redirect to the requirement's own show page
    return redirect(url_for("requirement_show",
                            requirement_id=requirement.get("_id")))
    '''
    return redirect(url_for("document_edit",
                            document_id=document.get('_id'),
                            requirement=requirement))


@app.route("/submissions/<document_id>", methods=["POST"])
def document_edit(document_id):
    """Update submitted document with new uploaded file."""
    # create an updated document
    file = request.files['userFile']
    file.save(file)
    updated_doc = {
        "file_name": file.filename,
        "requirement": requirement["name"]
    }

    # update the documents database
    documents.update_one(
        {'_id': ObjectId(document_id)},
        {"$set": {"file_name": file.filename}})

    # use the document to find the related requirement to redirect back to
    document = documents.find_one({"_id": ObjectId(document_id)})
    requirement = requirements.find_one({"name": document.get("requirement")})

    # redirect to the requirement's own show page
    return redirect(url_for("requirement_show",
                            requirement_id=requirement.get("_id")))


@app.route('/submission/download_zip')
def get_zip():
    """Takes all files in database and return a downloadable ZIP folder.
       V1: this is first iteration, just serving file back to user
    """
    return render_template("submission_complete.html")


if __name__ == '__main__':
    app.run(debug=True)
