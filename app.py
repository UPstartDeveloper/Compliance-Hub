from flask import Flask, render_template


app = Flask(__name__)


@app.route('/')
def index():
    """Work on file input page"""
    return render_template("home.html")


@app.route('/submission/form_tracker', methods=["POST"])
def form_tracker():
    """Track the forms which have been submitted so far."""
    return "Hi."


if __name__ == '__main__':
    app.run(debug=True)
