# -*- coding: utf-8 -*-

"""
Created on 2019/7/16

@author:hezhidong
"""
from sklearn.datasets import load_files
from sklearn2pmml import PMMLPipeline
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.linear_model import LogisticRegression
from sklearn.externals import joblib
import os
import sys


def getFirstContent(dataUrl, modelUrl, modelName):
    training_data = load_files(dataUrl, encoding="utf-8")
    '''
    这是开始提取特征，这里的特征是词频统计。
    '''
    count_vect = CountVectorizer()

    X_train_counts = count_vect.fit_transform(training_data.data)

    '''
    这是开始提取特征，这里的特征是TFIDF特征。
    '''
    tfidf_transformer = TfidfTransformer()

    X_train_tfidf = tfidf_transformer.fit_transform(X_train_counts)

    '''
    使用朴素贝叶斯分类,并做出简单的预测
    '''
    mnb_pipeline = PMMLPipeline([("classifier", LogisticRegression())])

    mnb_pipeline.fit(X_train_tfidf, training_data.target)
    
    //保存为pkl格式
    joblib.dump(mnb_pipeline, modelUrl + modelName)
    //保存为pmml格式
    sklearn2pmml(mnb_pipeline, modelUrl + modelName, with_repr = True)

    if (os.path.exists(modelUrl + modelName)):

        return "success"
    else:

        return "fail"


if __name__ == '__main__':
    a = []
    for i in range(1, len(sys.argv)):
        a.append((str(sys.argv[i])))

    print(getFirstContent(a[0], a[1], a[2]))
