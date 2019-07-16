# -*- coding: utf-8 -*-
"""
Created on 2019/7/16

@author:
"""

from sklearn.datasets import load_files
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.externals import joblib
import sys
import os
import numpy


def getTwoContent(mnb_pipeline, file, dataUrl, predictSavePath):
    mnb_pipeline = joblib.load(mnb_pipeline)

    testing_data = load_files(file, encoding="utf-8")

    training_data = load_files(dataUrl, encoding="utf-8")

    docs_test = testing_data.data

    count_vect = CountVectorizer()

    X_train_counts = count_vect.fit_transform(training_data.data)

    X_test_counts = count_vect.transform(docs_test)

    tfidf_transformer = TfidfTransformer()

    tfidf_transformer.fit_transform(X_train_counts)

    X_test_tfidf = tfidf_transformer.transform(X_test_counts)

    predicted = mnb_pipeline.predict(X_test_tfidf)

    numpy.savetxt(predictSavePath + 'new.csv', predicted, delimiter=',')

    if (os.path.exists(predictSavePath + 'new.csv')):

        return "success"
    else:

        return "fail"


if __name__ == '__main__':
    a = []
    for i in range(1, len(sys.argv)):
        a.append((str(sys.argv[i])))

    print(getTwoContent(a[0], a[1], a[2], a[3]))
