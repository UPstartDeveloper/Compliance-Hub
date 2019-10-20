from unittest import TestCase, main as unittest_main
from app import app
'''
test class created with inspiration from this tutorial:
https://tinyurl.com/y3kblnmy
'''


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

    def test_index(self):
        """Tests the requirements homepage."""
        result = self.client.get("/")
        self.assertEqual(result.status, "200 OK")
        self.assertIn(b"Welcome", result.data)


if __name__ == "__main__":
    unittest_main()
