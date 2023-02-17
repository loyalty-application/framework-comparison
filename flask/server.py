import os
import json
from flask import Flask, request, Response, jsonify
from werkzeug.utils import secure_filename
import pandas as pd

UPLOAD_FOLDER = './uploads'
app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@app.route('/')
def home():
    return "Hello"

@app.route('/transaction/file', methods=['POST'])
def upload_transaction_file():
    if request.method == 'POST':
      # check if the post request has the file part
        if 'file' not in request.files:
            return "Invalid File"
        file = request.files['file']
        # If the user does not select a file, the browser submits an
        # empty file without a filename.
        if file.filename == '' or file is None or file.filename is None:
            return "Invalid File"
        filename = secure_filename(file.filename)
        file_fullpath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(file_fullpath)

        # read csv file
        df = pd.read_csv(file_fullpath)
        total = df.loc[df['merchant'] == "A", 'amount'].sum()
        print(total)
        return jsonify(total = total)
    return Response("Invalid Request", status=400)


@app.route('/transaction', methods=['POST'])
def create_transaction():
    if request.method == 'POST':
        data = request.get_json(force=True)
        if data is None:
            return Response("Bad request" + str(data), status=400)    

        
        total = 0
        for x in data['transactions']:
            if x['merchant'] == "A":
                total += float( x["amount"] ) 

        return jsonify(total = total)
                
    return Response("Invalid Request", status=400)




