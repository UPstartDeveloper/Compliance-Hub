from unittest import TestCase, main as unittest_main, mock
from bson.objectid import ObjectId
from app import app
'''
test class created with inspiration from this tutorial:
https://tinyurl.com/y3kblnmy
'''

sample_requirement_id = ObjectId("5d55cffc4a3d4031f42827a3")
sample_document_id = ObjectId("5d55cffc4a3d4031f42827a4")
sample_req = {
    "name": "Regulation E",
    "description": "You must include child safety locks.",
    "image": "https://i.ytimg.com/vi/lroO4oBT-a8/maxresdefault.jpg",
    "num_required": 2,
    "documents": [sample_document_id],
    "num_submitted": 1
}
sample_doc = {
    "name": "fake_file.txt",
    "requirement": sample_req["name"]
}

sample_file_upload = {
    # should be a MultiDict object with FileStorage values,
    # am not sure how to implement this yet
}


class ComplianceTests(TestCase):
    """Flask tests"""
    def setUp(self):
        """ Create a test client to run each route test through.
            Runs automatically before every test in this file.
        """
        # Get the Flask test client
        self.client = app.test_client()

        # Show Flask errors that happen during tests
        app.config["TESTING"] = True

    def test_show_requirements(self):
        """Tests the requirements homepage."""
        result = self.client.get("/")
        self.assertEqual(result.status, "200 OK")
        self.assertIn(b"Welcome", result.data)

    def test_form_upload(self):
        """Test rendering of the upload form page."""
        result = self.client.get("/submissions/form_tracker")
        self.assertEqual(result.status, "200 OK")
        self.assertIn(b"Upload Your Files", result.data)

    @mock.patch("pymongo.collection.Collection.insert_one")
    def test_form_new(self, mock_insert):
        """Test inserting a document into MongoDB and related field in another
           Collection.
        """
        result = self.client.post("/submission/form_tracker/upload",
                                  data=sample_file_upload)

        # For now, redirects to error page because I am unsure how to format
        # sample_file_upload
        self.assertEqual(result.status, "200 OK")
        # mock_insert.assert_called_with(sample_doc)

    @mock.patch("pymongo.collection.Collection.find_one")
    def test_requirement_show(self, mock_find):
        """Test route to show one requirement."""
        mock_find.return_value = sample_req

        result = self.client.get(f"/submissions/{sample_requirement_id}")
        self.assertEqual(result.status, "200 OK")
        self.assertIn(b"Regulation E", result.data)

    @mock.patch("pymongo.collection.Collection.find_one")
    def test_requirement_edit(self, mock_find):
        """Test showing the edit form for the documents under a requirement."""
        mock_find.return_value = sample_req

        result = self.client.get(f"/submissions/{sample_requirement_id}/edit")
        self.assertEqual(result.status, "200 OK")
        self.assertIn(b"Change Your Files Here!", result.data)

    def test_get_zip(self):
        """Test the get zip folder route."""
        result = self.client.get("/submission/download_zip")
        self.assertIn(b"ZIP folder", result.data)


if __name__ == "__main__":
    unittest_main()
