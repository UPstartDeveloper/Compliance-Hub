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


@app.route('/')
def show_requirements():
    """Show user all the requireements that need to be completed."""
    return render_template("requirements_index.html",
                           requirements=requirements.find(),
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
    return render_template("document_edit.html",
                           document=document)


@app.route("/submissions/<document_id>", methods=["POST"])
def document_edit(document_id):
    """Update submitted document with new uploaded file."""
    document = documents.find_one({"_id": ObjectId(document_id)})
    # create an updated document
    file = request.files["userFile"]
    file.save(file)
    updated_doc = {
        "file_name": file.filename,
        "requirement": document["requirement"]
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


@app.route('/submissions/<requirement_id>/delete', methods=["POST"])
def submissions_delete(requirement_id):
    """Show delete form for a requirement's related documents."""
    requirement = requirements.find_one({"_id": ObjectId(requirement_id)})
    related_docs = documents.find({"requirement": requirement.get("name")})

    # render form
    return render_template("submissions_delete.html",
                           documents=related_docs)


@app.route('/submissions/delete_docs', methods=["POST"])
def delete_submissions():
    """Remove documents marked by user."""
    doc_ids_to_remove = request.form.getlist("document")  # list of str
    # get a list of the documents to remove from the ids
    docs_to_delete = list()
    for id in doc_ids_to_remove:
        doc = documents.find_one({"_id": ObjectId(id)})
        docs_to_delete.append(doc)
    # find the requirement related to these documents
    doc = docs_to_delete[0]
    requirement = requirements.find_one({"name": doc.get("requirement")})
    # delete the documents one-by-one
    for document in docs_to_delete:
        documents.delete_one(document)

    return redirect(url_for("requirement_show",
                            requirement_id=requirement.get('_id')))


@app.route('/submission/download_zip')
def get_zip():
    """Takes all files in database and return a downloadable ZIP folder.
       V1: this is first iteration, just serving file back to user
    """
    return render_template("submission_complete.html")


if __name__ == '__main__':
    app.run(debug=True)
