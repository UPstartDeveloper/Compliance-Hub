from flask import Flask

app = Flask(__name__)


@app.route('/')
def index():
    """Display welcome message."""
    return "Hello World! What are you working on today?"


if __name__ == '__main__':
    app.run(debug=True)
