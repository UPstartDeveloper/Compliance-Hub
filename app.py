from flask import Flask, render_template

app = Flask(__name__)


@app.route('/')
def index():
    """Work on file input page"""
    return render_template("home.html")


if __name__ == '__main__':
    app.run(debug=True)
